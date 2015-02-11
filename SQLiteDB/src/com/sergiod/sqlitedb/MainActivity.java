package com.sergiod.sqlitedb;

import java.util.Random;

import android.support.v7.app.ActionBarActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	public static final int MIN_COMPRIMIDOS = 6;
	public static final int MAX_COMPRIMIDOS = 34;
	private static final int MEDICAMENTOS_INSERTADOS = 2;
	
	private SQLiteDatabase dataBase = null;
	private EditText etNombre;
	private EditText etComprimidos;
	private TextView listado;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		etNombre = (EditText) findViewById(R.id.et_nombre);
		etComprimidos = (EditText) findViewById(R.id.et_num_comp);
		listado = (TextView) findViewById(R.id.tv_listado);

		// Abrimos / Creamos la base de datos DBMedicamentos
		MedicamentosSQLOpenHelper medicamentosHelper = new MedicamentosSQLOpenHelper(
				this, "DBMedicamentos", null, 1);
		// Conectamos con la BBDD en MODO DE ESCRITURA
		dataBase = medicamentosHelper.getWritableDatabase();

		/**
		 * Ahora que ya hemos conseguido una referencia a la base de datos
		 * (objeto de tipo SQLiteDatabase) ya podemos realizar todas las
		 * acciones que queramos sobre ella. 
		 * 
		 */
		
		// INSERTAMOS UNOS DATOS INICIALES
				// PRIMERO COMPRUEBO QUE SE HA ABIERTO CORRECTAMENTE LA BASE DE DATOS
				if (dataBase != null) {
					Random numAleatorio = new Random();
					// Insertamos 2 medicamentos de ejemplo
					for (int i = 1 ; i < MEDICAMENTOS_INSERTADOS + 1 ; i++) {
						// Generamos los datos
						int codigo = i;
						String nombre = "Medicamento " + i;
						int comprimidos = MIN_COMPRIMIDOS + numAleatorio.nextInt(MAX_COMPRIMIDOS + 1);
			
						// Insertamos los datos en la tabla Medicamentos
						dataBase.execSQL("INSERT INTO Medicamentos (codigo, nombre, comprimidos) "
								+ "VALUES (" + codigo + ", '" + nombre + "', " + comprimidos + ")");
						
						//OTRA FORMA DE HACER UNA INSERCION SERIA
										
						//				ContentValues valores =new ContentValues();
						//				//metemos los datos en nuestro contenedor
						//				valores.put("codigo",i);
						//				valores.put("nombre",nombre);
						//				valores.put("comprimidos",comprimidos);
						//				//insertamos los datos en la base
						//				dataBase.insert("medicamentos",null, valores);

					}
				}
						
	}
	
	public void addMedicamento(View v){
		int codigo = 0;
		String sqlCodigo = "select count(*) from medicamentos";
		Cursor c = dataBase.rawQuery(sqlCodigo, null);
		if(c.moveToFirst()){
			codigo = Integer.parseInt(c.getString(0)) + 1;
		}
		
		String nombre = etNombre.getText().toString();
		int comprimidos = Integer.parseInt(etComprimidos.getText().toString());
		
		dataBase.execSQL("INSERT INTO Medicamentos (codigo, nombre, comprimidos) "
				+ "VALUES (" + codigo + ", '" + nombre + "', " + comprimidos + ")");
		Toast.makeText(getApplicationContext(), "Medicamento añadido correctamente", Toast.LENGTH_SHORT).show();
	}
	
	public void listarMedicamentos(View v){
		this.listado.setText("");
		String codigo = null;
		String nombre = null;
		String comprimidos = null;
		String listado = "LISTADO: ";
		String sqlMedicamento = "select codigo, nombre, comprimidos from medicamentos";
		Cursor c = dataBase.rawQuery(sqlMedicamento, null);
		if(c.moveToFirst()){
			do {
				codigo = c.getString(0);
				nombre = c.getString(1);
				comprimidos = c.getString(2);
				listado += "\n" + codigo + "        " + nombre + "        " + comprimidos; 
			} while(c.moveToNext());
			
		}
		this.listado.setText(listado);
		
	}
	
	public void deleteClose(View v){
		dataBase.execSQL("DELETE from medicamentos");
		dataBase.close();
		finish();
	}
	
	
											///////////////////
											////// MENUS //////
											///////////////////
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
