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
		 * será ejecutado automáticamente por nuestra clase MedicamentosSQLOpenHelper
		 *  cuando sea necesaria la creación de la base de datos, es decir, cuando aún 
		 *  no exista. Las tareas típicas que deben hacerse en este método serán la creación 
		 *  de todas las tablas necesarias y la inserción de los datos iniciales si son necesarios.
		 */
		// CREAMOS LA TABLA MEDIANTE LA EJECUCION DE LA SENTENCIA sqlCreate
		db.execSQL(sqlCreate);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		/**
		 * el método onUpgrade() se lanzará automáticamente cuando sea necesaria 
		 * una actualización de la estructura de la base de datos o una conversión 
		 * de los datos.
		 */

	}

}
