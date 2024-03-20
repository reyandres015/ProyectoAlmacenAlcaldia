# Proyecto Gestión Almacen Entidad Pública

Proyecto realizado a comienzos del 2023 para la gestion de inventarios de documentos, elementos fisicos y devolutivos de un almacen de una entidad pública.

## Requisitos 🧩

- Java Development Kit (JDK) 8 o superior instalado.

## Ejecución ▶️

### Paso 1: Clonar el Repositorio

Clona este repositorio en tu máquina local utilizando el siguiente comando:

```bash
git clone https://github.com/reyandres015/ProyectoAlmacenAlcaldia.git
```

### Paso 2: Moverte a la carpeta creada
```bash
cd ProyectoAlmacen
```
### Paso 3: Ejecutar .jar
```bash
java -jar "ProyectoAlmacen.jar" 
```

## Interfaz grafica

### Ingreso contratos

Este es el primer elemento a registrar antes que cualquier otro. Se registran datos como la fecha, Referencia, Objeto del contrato, Valor, Proveedor, Identificación del proveedor, nombre del representante legal del proveedor, identificación, Dirección, Correo, Celular.

Desde manera se crea un contrato para luego ingresar sus productos.

![image](https://github.com/reyandres015/ProyectoAlmacenAlcaldia/assets/73958988/f3079394-bc68-4868-a6f7-ce3a37f13153)

### Ingreso productos

En esta segunda ventana es en donde se ingresa cada producto proviniente del contrato antes registrado.
![image](https://github.com/reyandres015/ProyectoAlmacenAlcaldia/assets/73958988/cb56edf0-077e-478a-951e-87f46dbd9367)

### Tabla Productos

Una vez registrados los productos se podran ver en un consolidado general para todos los productos registrados en el programa.
![image](https://github.com/reyandres015/ProyectoAlmacenAlcaldia/assets/73958988/ce6abcd5-20e8-49ed-9b42-27fa21d70cf8)

### Tabla Contratos

Existe tambien una consolidado general de los contratos registrados en el programa.

![image](https://github.com/reyandres015/ProyectoAlmacenAlcaldia/assets/73958988/f140e94f-5de1-4d76-8986-2cfbb75c9acb)


### Base de datos 😶‍🌫️

En este proyecto quedó pendiente la implementacion de una base de datos relacional o no relacional, debido a que actualmente usa un archivo de tipo .DAT para guardar todos los datos localmente.

![image](https://github.com/reyandres015/ProyectoAlmacenAlcaldia/assets/73958988/4fc0f546-d9ed-4551-b331-8c386d0fa0fb)

