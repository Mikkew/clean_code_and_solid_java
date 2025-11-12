package com.mms.solid.p03_liskov_substitution;

// Versión que viola el Principio de Sustitución de Liskov (LSP):

// Clase base
class RectanguloBad {
    protected Integer ancho;
    protected Integer alto;

    public void setAncho(Integer ancho) {
        this.ancho = ancho;
    }

    public void setAlto(Integer alto) {
        this.alto = alto;
    }

    public Integer getArea() {
        return ancho * alto;
    }
}

// Subclase que viola el LSP
class CuadradoBad extends RectanguloBad {

    @Override
    public void setAncho(Integer ancho) {
        this.ancho = ancho;
        this.alto = ancho; // ¡Forzamos que sea un cuadrado!
    }

    @Override
    public void setAlto(Integer alto) {
        this.ancho = alto;
        this.alto = alto; // ¡Ambos lados deben ser iguales!
    }
}

//*************************************************************************

// Versión corregida aplicando el Principio de Sustitución de Liskov (LSP):

// Interfaz base que define un contrato claro
interface Figura {
    Integer getArea();
}

class Rectangulo implements Figura {
    private Integer ancho;
    private Integer alto;

    public Rectangulo(Integer ancho, Integer alto) {
        this.ancho = ancho;
        this.alto = alto;
    }

    public void setAncho(Integer ancho) {
        this.ancho = ancho;
    }

    public void setAlto(Integer alto) {
        this.alto = alto;
    }

    @Override
    public Integer getArea() {
        return ancho * alto;
    }
}

class Cuadrado implements Figura {
    private Integer lado;

    public Cuadrado(Integer lado) {
        this.lado = lado;
    }

    public void setLado(Integer lado) {
        this.lado = lado;
    }

    @Override
    public Integer getArea() {
        return lado * lado;
    }
}

public class Liskov_Substitution {
     public static void imprimirArea(Figura figura) {
        System.out.println("Área: " + figura.getArea());
    }

    public static void main(String[] args) {
        // Ejemplo que viola LSP
        RectanguloBad rect = new CuadradoBad(); // Sustitución válida en sintaxis
        rect.setAncho(5);
        rect.setAlto(4);

        // ¡Esperamos un área de 5 * 4 = 20!
        // Pero el Cuadrado sobrescribe setAlto() y setAncho() → ambos valen 4
        System.out.println("Área esperada: 20, Área real: " + rect.getArea()); // Imprime 16 ❌


        // Ejemplo que cumple LSP
        imprimirArea(new Rectangulo(5, 4)); // 20
        imprimirArea(new Cuadrado(4));      // 16
        // No hay sustitución problemática: cada clase cumple su contrato.
    }
}
