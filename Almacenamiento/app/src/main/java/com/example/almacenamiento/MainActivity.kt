package com.example.almacenamiento

import android.content.Context
import android.os.Build.ID
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    // Almacenamiento Interno
    /*
    val nombreArchivoAI = "miarchivoAI.txt"
    val datosAI = "Contenido del archivo en el almacenamiento interno"
     */
    //FIN

    //Almacenamiento Externo
   // val nombreArchvivoAE = "miarchivoAE.txt"
   // val datosAE = "Contenido del archivo en el almacenamiento externo"
    //FIN

    //Almacenamiento Cahce
   // val clave = "Clave"
   // val valor = "Mi valor de cache"

    //Almacenamiento SQLite
    val  datasHelper = DatasHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //INICIO Almacenamiento Interno
       //escribirDatosAlmacenamientoInterno(nombreArchivoAI,datosAI)


        //INICIO Almacenamiento Externo
        // escribirDatosAlmacenamientoExterno(nombreArchvivoAE,datosAE)

        //Almacenamiento Cache
       // escribirDatosAlmacenamientoCache(this,clave,valor)

        //Almacenamiento SQLite
        datasHelper.addContact("Martin","123456789")

    }

    override fun onResume() {
        super.onResume()

        //INICO
        /* Almacenamiento interno
        val contenido = leerDatosAlmacenamientoInterno(nombreArchivoAI)
        Toast.makeText(this,contenido,Toast.LENGTH_LONG).show()
        */
        //FIN

        //Almacenamiento externo
        //val contenido = leerDatosAlmacenamientoExterno(nombreArchvivoAE)
        // Toast.makeText(this,contenido,Toast.LENGTH_LONG).show()

        //Almacenamiento Cache
        //val contenido = leerDatosAlmacenamientoCache(this,clave)
       // Toast.makeText(this,contenido,Toast.LENGTH_LONG).show()

        //Almacenamiento SQLite
        val contenido = datasHelper.getAllContacts()
        for (contact in contenido)
        {
            Toast.makeText(this,"ID: ${contact.id}, Name: ${contact.name}, Phone: ${contact.phone}",Toast.LENGTH_LONG).show()
        }
    }

    //INICIO
    /*
    fun escribirDatosAlmacenamientoInterno(nombreArchivo: String, datos: String){

        val archivo = File(this.filesDir,nombreArchivo)
        archivo.writeText(datos)

    }

    fun leerDatosAlmacenamientoInterno(nombreArchivo: String): String{
        val archivo = File(this.filesDir, nombreArchivo)
        return archivo.readText()
    }

     */
    //FIN

    // Almacenamiento Externo Inicio
    /*
    private fun escribirDatosAlmacenamientoExterno(nombreArchivo: String, datos: String) {

        val estado = isExternalStorageWritable()
        if(estado) {
            val directorio = getExternalFilesDir(null)
            val archivo = File(directorio,nombreArchvivoAE)
        try{
            FileOutputStream(archivo).use{
                it.write(datosAE.toByteArray())
            }

        }catch (e:IOException){
        e.printStackTrace()}
        }
    }

    private fun leerDatosAlmacenamientoExterno(nombreArchivo: String):String{
        val estado = isExternalStorageReadable()
        if(estado) {
            val directorio = getExternalFilesDir(null)
            val archivo = File(directorio,nombreArchvivoAE)
            var fileInputStream = FileInputStream(archivo)
            var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder : StringBuilder = StringBuilder()
            var text: String? = null
            while ({text = bufferedReader.readLine(); text}() != null){
                stringBuilder.append(text)
            }
            fileInputStream.close()

            return stringBuilder.toString()
        }
        return ""
    }
    //FIN

    private fun isExternalStorageWritable(): Boolean {
        val estado = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED == estado
    }

    private fun isExternalStorageReadable() : Boolean {
        val estado = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED == estado || Environment.MEDIA_MOUNTED_READ_ONLY == estado
    }
     */
    // FIN

    //Almacenamiento Cache
   /* fun escribirDatosAlmacenamientoCache(context: Context, clave: String, valor:String){
        val sharedPreferences = context.getSharedPreferences("cache",Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(clave,valor)
        editor.apply()
    }

    fun leerDatosAlmacenamientoCache(context: Context, clave: String): String? {
        val sharedPreferences = context.getSharedPreferences("cache",Context.MODE_PRIVATE)
        return sharedPreferences.getString(clave,null)
    }
    */


}

