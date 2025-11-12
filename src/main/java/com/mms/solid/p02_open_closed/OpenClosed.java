package com.mms.solid.p02_open_closed;

// Versión que viola el Principio de Abierto/Cerrado (OCP):

class RectanguloBad {
    private Double ancho;
    private Double altura;

    public RectanguloBad(Double ancho, Double altura) {
        this.ancho = ancho;
        this.altura = altura;
    }

    public Double getAncho() {
        return ancho;
    }

    public Double getAltura() {
        return altura;
    }
}

class CirculoBad {
    private Double radio;

    public CirculoBad(Double radio) {
        this.radio = radio;
    }

    public double getRadio() {
        return radio;
    }
}

// Esta clase viola el OCP: si queremos añadir un nuevo tipo de figura,
// ¡tenemos que modificar el método calcularAreaTotal!
class CalculadoraAreaBad {
    public double calcularAreaTotal(Object[] figuras) {
        Double areaTotal = 0.0;
        for (Object figura : figuras) {
            if (figura instanceof Rectangulo) {
                RectanguloBad rectangulo = (RectanguloBad) figura;
                areaTotal += rectangulo.getAncho() * rectangulo.getAltura();
            } else if (figura instanceof CirculoBad) {
                CirculoBad circulo = (CirculoBad) figura;
                areaTotal += Math.PI * circulo.getRadio() * circulo.getRadio();
            }
            // ¡Si añadimos un Triángulo, hay que modificar este método!
        }
        return areaTotal;
    }
}

//*************************************************************************

// Versión corregida aplicando el Principio de Abierto/Cerrado (OCP):

// Paso 1: Definimos una abstracción que todas las figuras deben implementar
interface Figura {
    Double calcularArea(); // Cada figura sabe cómo calcular su propia área
}

// Paso 2: Cada figura implementa su lógica
class Rectangulo implements Figura {
    private Double ancho;
    private Double altura;

    public Rectangulo(Double ancho, Double altura) {
        this.ancho = ancho;
        this.altura = altura;
    }

    @Override
    public Double calcularArea() {
        return ancho * altura;
    }
}

class Circulo implements Figura {
    private Double radio;

    public Circulo(Double radio) {
        this.radio = radio;
    }

    @Override
    public Double calcularArea() {
        return Math.PI * (Math.pow(radio, 2));
    }
}

// Paso 3: La calculadora ahora depende de la abstracción (Figura), no de clases concretas
class CalculadoraArea {
    // ¡Este método NO necesita cambiar si añadimos nuevas figuras!
    public double calcularAreaTotal(Figura[] figuras) {
        double areaTotal = 0;
        for (Figura figura : figuras) {
            areaTotal += figura.calcularArea(); // Polimorfismo ✅
        }
        return areaTotal;
    }
}

// Paso 4: Podemos añadir nuevas figuras SIN modificar la calculadora
class Triangulo implements Figura {
    private Double base;
    private Double altura;

    public Triangulo(Double base, Double altura) {
        this.base = base;
        this.altura = altura;
    }

    @Override
    public Double calcularArea() {
        return (base * altura) / 2;
    }
}


public class OpenClosed {
    public static void main(String[] args) {
        Figura[] figuras = {
            new Rectangulo(5.0, 4.0),
            new Circulo(3.0),
            new Triangulo(6.0, 4.0) // ¡Nuevo! Sin tocar CalculadoraArea
        };

        CalculadoraArea calculadora = new CalculadoraArea();
        Double areaTotal = calculadora.calcularAreaTotal(figuras);
        System.out.println("Área total: " + areaTotal);
    }
}
