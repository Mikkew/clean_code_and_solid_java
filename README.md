# 📘 Principios SOLID

Este repositorio contiene una explicación detallada de los principios **SOLID**, un conjunto de buenas prácticas de diseño de software que ayudan a crear sistemas más mantenibles, flexibles y fáciles de probar.

---

## 🧱 ¿Qué es SOLID?

**SOLID** es un acrónimo creado por Michael Feathers y popularizado por Robert C. Martin (*Uncle Bob*) en su libro *Agile Software Development: Principles, Patterns, and Practices*.  
Agrupa cinco principios de diseño ampliamente aceptados en la industria:

1. **S – Single Responsibility Principle (SRP)**  
   Una clase debe tener **una única responsabilidad** o razón para cambiar.  
   - Evita clases con múltiples responsabilidades.  
   - Mejora la legibilidad, mantenibilidad y testabilidad.
   - **Ejemplo**: [`SingleResponsability.java`](./src/main/java/com/mms/solid/p01_single_responsibility/SingleResponsability.java)

2. **O – Open/Closed Principle (OCP)**  
   Los módulos deben estar **abiertos para extensión pero cerrados para modificación**.  
   - Se logra mediante herencia, composición o patrones de diseño.  
   - Permite añadir nuevas funcionalidades sin alterar el código existente.
   - **Ejemplo**: [`OpenClosed.java`](./src/main/java/com/mms/solid/p02_open_closed/OpenClosed.java)

3. **L – Liskov Substitution Principle (LSP)**  
   Las clases derivadas deben poder **sustituir a sus clases base sin alterar el comportamiento esperado**.  
   - Garantiza polimorfismo seguro.  
   - Ejemplo clásico: el problema de modelar un cuadrado como subclase de rectángulo.
   - **Ejemplo**: [`LiskovSubstitution.java`](./src/main/java/com/mms/solid/p03_liskov_substitution/LiskovSubstitution.java)

4. **I – Interface Segregation Principle (ISP)**  
   Los clientes no deben depender de métodos que no utilizan.  
   - Favorece **interfaces pequeñas y específicas**.  
   - Evita contratos demasiado grandes que obliguen a implementaciones innecesarias.
   - **Ejemplo**: [`InterfaceSegregation.java`](./src/main/java/com/mms/solid/p04_interface_segregation/InterfaceSegregation.java)

5. **D – Dependency Inversion Principle (DIP)**  
   Los módulos de alto nivel no deben depender de módulos de bajo nivel, **ambos deben depender de abstracciones**.  
   - Se implementa mediante **interfaces y la inyección de dependencias**.  
   - Aumenta la tolerancia al cambio y reduce el acoplamiento.
   - **Ejemplo**: [`DependencyInversion.java`](./src/main/java/com/mms/solid/p05_dependency_inversion/DependencyInversion.java)

---

## ⚖️ Beneficios de aplicar SOLID

- Código más **mantenible** y **flexible**.  
- Mayor **testabilidad** y facilidad para aplicar TDD.  
- Arquitecturas más **robustas y escalables**.  
- Reducción de la **complejidad accidental**.  
- Facilita el trabajo en equipo y la colaboración.
- Mejora la **comprensión del código** para nuevos desarrolladores.
- Fomenta el uso de **patrones de diseño** y buenas prácticas.
- Promueve la **reutilización de código** y componentes.
- Facilita la **refactorización** y evolución del software.
- Ayuda a **identificar responsabilidades** claras en el diseño del sistema.
- Reduce el **acoplamiento** entre módulos y componentes.
- Mejora la **cohesión** dentro de las clases y módulos.
- Facilita la **documentación** y el entendimiento del sistema.
- Promueve una **arquitectura orientada a servicios** y microservicios.
- Ayuda a cumplir con los **requisitos cambiantes** del negocio.