package com.example.miagenda

class Agenda{
    var contactos = mutableListOf<Contacto>()

    fun agregarContacto(contacto: Contacto){
        contactos.add(contacto)
    }
    fun listarContactos(){
        for(contacto in contactos){
            println("${contacto.nombre} - ${contacto.telefono}")
        }
    }
    fun buscarContacto(nombre:String):Contacto?{
        for(contacto in contactos){
            if(contacto.nombre.equals(nombre,true)){
                return contacto
            }
        }
        return null
    }
    fun borrarContacto(nombre:String):Boolean{
        for(contacto in contactos){
            if(contacto.nombre.equals(nombre,true)){
                contactos.remove(contacto)
                return true
            }
        }
        return false
    }
    fun editarContacto(){

    }
}