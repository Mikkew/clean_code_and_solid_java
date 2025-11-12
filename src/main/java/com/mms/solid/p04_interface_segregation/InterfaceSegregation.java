package com.mms.solid.p04_interface_segregation;

// Versión que viola el Principio de Segregación de Interfaces (ISP):

// Interfaz "grasa" que fuerza a todas las clases a implementar métodos que no usan
interface TrabajadorBad {
    void trabajar();
    void comer();
    void dormir();
    void programar();      // ¡Solo los desarrolladores programan!
    void manejarMaquinaria(); // ¡Solo los operarios lo hacen!
}

class DesarrolladorBad implements TrabajadorBad {
    @Override
    public void trabajar() {
        System.out.println("Desarrollador trabajando...");
    }

    @Override
    public void comer() {
        System.out.println("Desarrollador comiendo...");
    }

    @Override
    public void dormir() {
        System.out.println("Desarrollador durmiendo...");
    }

    @Override
    public void programar() {
        System.out.println("Escribiendo código Java...");
    }

    // ¡VIOLACIÓN DEL ISP! Este método no tiene sentido para un desarrollador
    @Override
    public void manejarMaquinaria() {
        // ¿Qué hacemos aquí? ¿Lanzar una excepción?
        throw new UnsupportedOperationException("Un desarrollador no maneja maquinaria");
    }
}

class OperarioDeFabricaBad implements TrabajadorBad {
    @Override
    public void trabajar() {
        System.out.println("Operario trabajando en la fábrica...");
    }

    @Override
    public void comer() {
        System.out.println("Operario comiendo...");
    }

    @Override
    public void dormir() {
        System.out.println("Operario durmiendo...");
    }

    // ¡Otra violación! El operario no programa
    @Override
    public void programar() {
        throw new UnsupportedOperationException("El operario no programa");
    }

    @Override
    public void manejarMaquinaria() {
        System.out.println("Manejando maquinaria industrial...");
    }
}

//*************************************************************************

// Versión corregida aplicando el Principio de Segregación de Interfaces (ISP):

// Interfaces pequeñas y específicas ✅

interface Trabajador {
    void trabajar();
}

interface Comedor {
    void comer();
}

interface Durmiente {
    void dormir();
}

interface Programador {
    void programar();
}

interface OperarioMaquinaria {
    void manejarMaquinaria();
}

// El desarrollador solo implementa lo que usa
class Desarrollador implements Trabajador, Comedor, Durmiente, Programador {
    @Override
    public void trabajar() {
        System.out.println("Desarrollador trabajando...");
    }

    @Override
    public void comer() {
        System.out.println("Desarrollador comiendo...");
    }

    @Override
    public void dormir() {
        System.out.println("Desarrollador durmiendo...");
    }

    @Override
    public void programar() {
        System.out.println("Escribiendo código limpio en Java...");
    }
}

// El operario solo implementa lo relevante para su rol
class OperarioDeFabrica implements Trabajador, Comedor, Durmiente, OperarioMaquinaria {
    @Override
    public void trabajar() {
        System.out.println("Operario trabajando en la línea de producción...");
    }

    @Override
    public void comer() {
        System.out.println("Operario comiendo en la cafetería...");
    }

    @Override
    public void dormir() {
        System.out.println("Operario descansando...");
    }

    @Override
    public void manejarMaquinaria() {
        System.out.println("Operando prensa hidráulica...");
    }
}

public class InterfaceSegregation {
    // Métodos que aceptan solo lo que necesitan
    public static void hacerTrabajar(Trabajador trabajador) {
        trabajador.trabajar();
    }

    public static void alimentar(Comedor comedor) {
        comedor.comer();
    }

    public static void codificar(Programador programador) {
        programador.programar();
    }

    public static void main(String[] args) {
        Desarrollador dev = new Desarrollador();
        OperarioDeFabrica operario = new OperarioDeFabrica();

        hacerTrabajar(dev);      // OK
        hacerTrabajar(operario); // OK

        codificar(dev);          // OK
        // codificar(operario);  // ❌ ¡ERROR DE COMPILACIÓN! → Bueno: se detecta temprano

        alimentar(dev);          // OK
        alimentar(operario);     // OK
    }
}