import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Banco banco = new Banco();
        boolean estado = true;

        while (estado) {
            System.out.println("""
                    1. CREAR CLIENTE
                    2. MOSTRAR CLIENTES
                    3. MOSTRAR INFORMACIÓN DE UNA CUENTA
                    4. DEPOSITAR DINERO
                    5. RETIRAR DINERO
                    6. TRANSFERENCIA ENTRE CUENTAS
                    7. ELIMINAR CUENTA
                    8. BUSCAR CUENTA POR TITULAR
                    9. MOSTRAR HISTORIAL DE UNA CUENTA
                    10. SALIR
                    """);
            int op = teclado.nextInt();

            switch (op) {
                case 1 -> {
                    System.out.println("INGRESE EL NOMBRE DEL TITULAR");
                    teclado.nextLine(); // Consumir salto de línea
                    String titular = teclado.nextLine();
                    System.out.println("INGRESE EL SALDO DE LA CUENTA");
                    double saldo = teclado.nextDouble();
                    System.out.println("INGRESE EL NÚMERO DE CUENTA");
                    String numeroCuenta = teclado.next();
                    System.out.println("CREA TU PIN DE SEGURIDAD");
                    String pin = teclado.next();

                    CuentaBancario nuevaCuenta = new CuentaBancario();
                    nuevaCuenta.setTitular(titular);
                    nuevaCuenta.setSaldo(saldo);
                    nuevaCuenta.setNumeroCuenta(numeroCuenta);
                    nuevaCuenta.setPin(pin);
                    banco.agregarCuenta(nuevaCuenta);
                    System.out.println("CLIENTE CREADO CON EXITO.");
                }
                case 2 -> {
                    System.out.println("MOSTRANDO CLIENTES...");
                    if (banco.getCuentas().isEmpty()) {
                        System.out.println("NO HAY CLIENTES REGISTRADOS.");
                    } else {
                        for (CuentaBancario cu : banco.getCuentas()) {
                            System.out.println(cu);
                        }
                    }
                }
                case 3 -> {
                    System.out.println("INGRESE EL NÚMERO DE CUENTA");
                    String nCuenta = teclado.next();
                    CuentaBancario cuenta = banco.buscarPorNumero(nCuenta);
                    if (cuenta != null) {
                        System.out.println(cuenta);
                    } else {
                        System.out.println("CUENTA NO ENCONTRADA.");
                    }
                }
                case 4 -> {
                    System.out.println("INGRESE EL NÚMERO DE CUENTA");
                    String numeroCuenta = teclado.next();
                    CuentaBancario cuenta = banco.buscarPorNumero(numeroCuenta);

                    if (cuenta != null) {
                        System.out.println("INGRESE SU PIN");
                        String pinIngresado = teclado.next();
                        if (cuenta.verificarPin(pinIngresado)) {
                            System.out.println("CUÁNTO DESEA DEPOSITAR:");
                            double monto = teclado.nextDouble();
                            cuenta.depositarDinero(monto);
                        } else {
                            System.out.println("PIN INCORRECTO. TRANSACCION CANCELADA.");
                        }
                    } else {
                        System.out.println("CUENTA NO ENCONTRADA.");
                    }
                }
                case 5 -> {
                    System.out.println("INGRESE EL NÚMERO DE CUENTA");
                    String numeroCuenta = teclado.next();
                    CuentaBancario cuenta = banco.buscarPorNumero(numeroCuenta);

                    if (cuenta != null) {
                        System.out.println("INGRESE SU PIN");
                        String pinIngresado = teclado.next();
                        if (cuenta.verificarPin(pinIngresado)) {
                            System.out.println("CUÁNTO DESEA RETIRAR:");
                            double monto = teclado.nextDouble();
                            cuenta.retirarDinero(monto);
                        } else {
                            System.out.println("PIN INCORRECTO. TRANSACCION CANCELADA.");
                        }
                    } else {
                        System.out.println("CUENTA NO ENCONTRADA.");
                    }
                }
                case 6 -> {
                    System.out.println("INGRESE EL NÚMERO DE CUENTA ORIGEN");
                    String numeroCuentaOrigen = teclado.next();
                    CuentaBancario cuentaOrigen = banco.buscarPorNumero(numeroCuentaOrigen);

                    if (cuentaOrigen != null) {
                        System.out.println("INGRESE SU PIN");
                        String pinIngresado = teclado.next();

                        if (cuentaOrigen.verificarPin(pinIngresado)) {
                            System.out.println("INGRESE EL NÚMERO DE CUENTA DESTINO");
                            String numeroCuentaDestino = teclado.next();
                            CuentaBancario cuentaDestino = banco.buscarPorNumero(numeroCuentaDestino);

                            if (cuentaDestino != null) {
                                System.out.println("CUÁNTO DESEA TRANSFERIR:");
                                double monto = teclado.nextDouble();
                                if (cuentaOrigen.getSaldo() >= monto) {
                                    cuentaOrigen.retirarDinero(monto);
                                    cuentaDestino.depositarDinero(monto);
                                    System.out.println("TRANSFERENCIA EXITOSA.");
                                } else {
                                    System.out.println("SALDO INSUFICIENTE.");
                                }
                            } else {
                                System.out.println("CUENTA DESTINO NO ENCONTRADA.");
                            }
                        } else {
                            System.out.println("PIN INCORRECTO. TRANSACCION CANCELADA.");
                        }
                    } else {
                        System.out.println("CUENTA ORIGEN NO ENCONTRADA.");
                    }
                }
                case 7 -> {
                    System.out.println("INGRESE EL NÚMERO DE CUENTA A ELIMINAR");
                    String nCuenta = teclado.next();
                    if (banco.eliminarCuenta(nCuenta)) {
                        System.out.println("CUENTA ELIMINADA CON EXITO.");
                    } else {
                        System.out.println("CUENTA NO ENCONTRADA.");
                    }
                }
                case 8 -> {
                    System.out.println("INGRESE EL NOMBRE DEL TITULAR");
                    teclado.nextLine(); // Consumir salto de línea
                    String nombre = teclado.nextLine();
                    var cuentas = banco.buscarPorTitular(nombre);
                    if (cuentas.isEmpty()) {
                        System.out.println("NO SE ENCONTRÓ NINGUNA CUENTA CON ESE TITULAR.");
                    } else {
                        cuentas.forEach(System.out::println);
                    }
                }
                case 9 -> {
                    System.out.println("INGRESE EL NÚMERO DE CUENTA PARA VER EL HISTORIAL");
                    String nCuenta = teclado.next();
                    CuentaBancario cuenta = banco.buscarPorNumero(nCuenta);
                    if (cuenta != null) {
                        System.out.println("HISTORIAL DE TRANSACCIONES: " + cuenta.getHistorial());
                    } else {
                        System.out.println("CUENTA NO ENCONTRADA.");
                    }
                }
                case 10 -> {
                    System.out.println("SALIENDO...");
                    estado = false;
                }
                default -> System.out.println("OPCION INVALIDA. INTENTE NUEVAMENTE.");
            }
        }
    }
}