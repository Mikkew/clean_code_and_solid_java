package com.mms.solid.p05_dependency_inversion;

// Versión que viola el Principio de Inversión de Dependencias (DIP):

// Implementación concreta de bajo nivel
class MySQLDatabaseBad {
    public void guardar(String mensaje) {
        System.out.println("Guardando en MySQL: " + mensaje);
    }
}

// Clase de alto nivel: viola el DIP porque depende directamente de MySQLDatabase
class GestorDeMensajesBad {
    private MySQLDatabaseBad db = new MySQLDatabaseBad(); // ❌ Dependencia concreta

    public void enviarMensaje(String mensaje) {
        // Lógica de negocio
        System.out.println("Procesando mensaje: " + mensaje);
        db.guardar(mensaje); // Usa la implementación concreta
    }
}

//*************************************************************************

// Versión corregida aplicando el Principio de Inversión de Dependencias (DIP):

// Paso 1: Definimos una abstracción (interfaz) para la operación de persistencia
interface RepositorioMensajes {
    void guardar(String mensaje);
}

// Paso 2: Implementaciones concretas (módulos de bajo nivel)
class MySQLRepositorio implements RepositorioMensajes {
    @Override
    public void guardar(String mensaje) {
        System.out.println("Guardando en MySQL: " + mensaje);
    }
}

class ArchivoRepositorio implements RepositorioMensajes {
    @Override
    public void guardar(String mensaje) {
        System.out.println("Guardando en archivo: " + mensaje);
    }
}

// Paso 3: Clase de alto nivel → depende SOLO de la abstracción ✅
class GestorDeMensajes {
    // Inyectamos la dependencia (no la creamos internamente)
    private final RepositorioMensajes repositorio;

    // Constructor: permite inyectar cualquier implementación que cumpla la interfaz
    public GestorDeMensajes(RepositorioMensajes repositorio) {
        this.repositorio = repositorio;
    }

    public void enviarMensaje(String mensaje) {
        System.out.println("Procesando mensaje: " + mensaje);
        repositorio.guardar(mensaje); // Usa la abstracción → no sabe qué implementación usa
    }
}

public class Dependency_Inversion {
    public static void main(String[] args) {
        // Podemos elegir la implementación en tiempo de ejecución
        RepositorioMensajes db = new MySQLRepositorio();
        GestorDeMensajes gestor1 = new GestorDeMensajes(db);
        gestor1.enviarMensaje("Hola desde MySQL");

        // ¡O usar otra sin cambiar el GestorDeMensajes!
        RepositorioMensajes archivo = new ArchivoRepositorio();
        GestorDeMensajes gestor2 = new GestorDeMensajes(archivo);
        gestor2.enviarMensaje("Hola desde archivo");
    }
}
