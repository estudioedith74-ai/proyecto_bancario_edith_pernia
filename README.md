Sistema Bancario — Java POO

Proyecto académico desarrollado para la materia de Programación Orientada a Objetos del Centro de Formación Profesional N°8 (SMATA). Modela el registro de clientes y cuentas de un banco, aplicando herencia, abstracción, encapsulamiento y asociación entre clases.

Descripción

El sistema administra dos tipos de clientes (individuales y empresa) y tres tipos de cuenta (caja de ahorro, cuenta corriente y cuenta convertibilidad), cada uno con reglas de negocio propias sobre cómo se deposita y se extrae dinero. El proyecto se construyó en dos etapas, siguiendo la consigna: primero el modelo base de clientes y cuentas, y después la incorporación de un nuevo producto (cuenta convertibilidad) para clientes empresa, que opera en pesos y en dólares.

Tecnologías utilizadas
Java 25
Maven, como gestor de dependencias y build del proyecto
Spring Boot, generado por Spring Initializr como base del proyecto. No se utiliza ningún componente de Spring en la lógica del sistema; el punto de entrada real para probar el código es una clase de test independiente, no ProyectoApplication.
Lombok, para evitar escribir a mano el código repetitivo de getters, setters, constructores y toString()
Estructura del proyecto
src/main/java/ar/com/edithpernia/proyectobancario/
├── ProyectoApplication.java         (punto de entrada de Spring Boot, sin uso en este ejercicio)
├── entidades/
│   ├── clientes/
│   │   ├── Cliente.java             (abstracta)
│   │   ├── ClienteIndividual.java   (extends Cliente)
│   │   └── ClienteEmpresa.java      (extends Cliente)
│   └── cuentas/
│       ├── Cuenta.java              (abstracta)
│       ├── CajaAhorro.java          (extends Cuenta)
│       ├── CuentaCorriente.java     (extends Cuenta)
│       ├── CuentaConvertibilidad.java (extends CuentaCorriente)
│       └── Cheque.java
└── tests/
    ├── TestSistemaBancario.java         (clientes, caja de ahorro y cuenta corriente)
    └── TestCuentaConvertibilidad.java   (cuenta convertibilidad)

Las entidades se dividieron en dos subpaquetes (clientes y cuentas) para modularizar el proyecto por dominio, en vez de mantener las ocho clases juntas en una sola carpeta.

Decisiones de diseño

Clases abstractas. Cuenta y Cliente no se pueden instanciar directamente: no existe conceptualmente "una cuenta" o "un cliente" sin más, solo sus tipos concretos. Esto se refuerza con extraerEfectivo, declarado como método abstracto en Cuenta: cada tipo de cuenta define su propia regla para extraer dinero (una caja de ahorro no permite superar el saldo; una cuenta corriente sí, mediante el descubierto autorizado), por lo que no existe una implementación común válida para todas.

Asociación tipada, no texto plano. Cuenta guarda una referencia real al objeto Cliente (private final Cliente clienteAsociado), no un nombre como cadena de texto. Esto permite acceder a los datos reales del cliente (DNI, CUIT) desde la cuenta, y refleja en el modelo que una cuenta pertenece a un cliente específico.

Atributos final como decisión de negocio. El número de cuenta y el cliente asociado se marcan final: una vez creada la cuenta, ninguno de los dos cambia. El saldo, en cambio, no es final, porque se modifica constantemente a través de las operaciones de depósito y extracción.

Encapsulamiento del saldo. El saldo nunca se expone con un setter público. Solo existe un método protected interno, utilizado por la propia clase y sus hijas para aplicar los cambios ya validados. La única forma de modificar el saldo desde fuera del sistema es a través de los métodos de negocio (depositarEfectivo, extraerEfectivo, depositarCheque, las conversiones de moneda), que validan cada operación antes de aplicarla.

Herencia sin duplicación. Las clases hijas solo declaran los métodos que son nuevos o que sobrescriben una regla distinta a la heredada; nunca repiten un método que heredan sin cambios.

Cheque como dependencia, no como asociación permanente. Cheque no guarda una relación de largo plazo con CuentaCorriente: se recibe como parámetro en depositarCheque, se usa para sumar su monto al saldo, y no queda almacenado en ningún lado. Por eso, en el diagrama de clases, la relación entre ambas se modela como una dependencia y no como una asociación con cardinalidad.

Validaciones con mensajes, no excepciones. Todas las operaciones de negocio validan sus parámetros con estructuras if/else, informando por consola cuando una operación no es válida (monto negativo, saldo insuficiente, cheque nulo), en lugar de interrumpir el programa con excepciones.

Cómo ejecutar

El proyecto no tiene interfaz gráfica ni expone una API; se valida mediante dos clases de prueba con método main, que crean clientes y cuentas, ejecutan operaciones y muestran el resultado por consola:

TestSistemaBancario.java: clientes individuales y empresa, caja de ahorro y cuenta corriente.
TestCuentaConvertibilidad.java: cuenta convertibilidad, incluyendo depósito en dólares y conversión entre monedas.

Cada línea relevante de los tests incluye un comentario con el resultado esperado, para poder comparar directamente contra la salida de la consola.

Posibles mejoras
Reemplazar double por BigDecimal para representar montos, evitando los errores de redondeo propios de la aritmética de punto flotante en operaciones financieras.
Incorporar una capa de persistencia (archivo o base de datos) en lugar de datos creados únicamente en memoria durante la ejecución del test.
Sumar pruebas automatizadas con JUnit, en reemplazo o complemento de las clases de test manuales con main.
Permitir el ingreso de datos por consola (Scanner) para operar el sistema de forma interactiva, en lugar de valores fijos en el código.
