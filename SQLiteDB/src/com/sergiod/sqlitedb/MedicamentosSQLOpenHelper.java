package com.sergiod.sqlitedb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class MedicamentosSQLOpenHelper extends SQLiteOpenHelper {
	
	

	//Sentencia SQL para crear la tabla de Usuarios
    String sqlCreate = "CREATE TABLE medicamentos (codigo INTEGER, nombre TEXT, comprimidos INTEGER)";

	public MedicamentosSQLOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		/**
		 * ser� ejecutado autom�ticamente por nuestra clase MedicamentosSQLOpenHelper
		 *  cuando sea necesaria la creaci�n de la base de datos, es decir, cuando a�n 
		 *  no exista. Las tareas t�picas que deben hacerse en este m�todo ser�n la creaci�n 
		 *  de todas las tablas necesarias y la inserci�n de los datos iniciales si son necesarios.
		 */
		// CREAMOS LA TABLA MEDIANTE LA EJECUCION DE LA SENTENCIA sqlCreate
		db.execSQL(sqlCreate);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		/**
		 * el m�todo onUpgrade() se lanzar� autom�ticamente cuando sea necesaria 
		 * una actualizaci�n de la estructura de la base de datos o una conversi�n 
		 * de los datos.
		 */

	}

}
