package com.mms.solid.p01_single_responsibility;

// Versión inicial que viola el Principio de Responsabilidad Única (SRP):

// Esta clase viola el SRP porque tiene varias responsabilidades:
// 1. Representar a un usuario.
// 2. Validar su correo electrónico.
// 3. Guardar en una base de datos.
// 4. Enviar un correo de bienvenida.

class UsuarioBad {
    private String nombre;
    private String email;

    public UsuarioBad(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    // Responsabilidad 1: lógica de negocio del usuario ✅
    public String getNombreCompleto() {
        return nombre;
    }

    // Responsabilidad 2: validación ✖️ (debería estar en otra clase)
    public boolean esEmailValido() {
        return email != null && email.contains("@");
    }

    // Responsabilidad 3: persistencia ✖️ (debería estar en un repositorio)
    public void guardarEnBaseDeDatos() {
        // Simulación de guardado
        System.out.println("Guardando usuario en la base de datos...");
    }

    // Responsabilidad 4: envío de correos ✖️ (debería estar en un servicio de notificaciones)
    public void enviarEmailDeBienvenida() {
        System.out.println("Enviando email de bienvenida a: " + email);
    }
}
//*************************************************************************


// Versión corregida aplicando el Principio de Responsabilidad Única (SRP):
// Responsabilidad única: representar a un usuario.
class Usuario {
    private String nombre;
    private String email;

    public Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    // Solo métodos relacionados con los datos del usuario
    public String getNombreCompleto() {
        return nombre;
    }
}

// Responsabilidad única: validar correos electrónicos.
class ValidadorEmail {
    public static boolean esEmailValido(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }
}

// Responsabilidad única: persistir usuarios.
class RepositorioUsuario {
    public void guardar(Usuario usuario) {
        // Lógica real para guardar en base de datos
        System.out.println("Guardando usuario " + usuario.getNombre() + " en la base de datos.");
    }
}

// Responsabilidad única: enviar correos electrónicos.
class ServicioEmail {
    public void enviarEmailDeBienvenida(Usuario usuario) {
        System.out.println("Enviando email de bienvenida a: " + usuario.getEmail());
    }
}

// Clase de caso de uso que coordina las responsabilidades
class RegistroDeUsuario {
    private RepositorioUsuario repositorio = new RepositorioUsuario();
    private ServicioEmail servicioEmail = new ServicioEmail();

    public void registrar(Usuario usuario) {
        // Validar email
        if (!ValidadorEmail.esEmailValido(usuario.getEmail())) {
            throw new IllegalArgumentException("Email inválido");
        }

        // Guardar usuario
        repositorio.guardar(usuario);

        // Enviar email de bienvenida
        servicioEmail.enviarEmailDeBienvenida(usuario);
    }
}

//*************************************************************************