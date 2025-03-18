import java.util.ArrayList;
import java.util.List;
public class CuentaBancario {
        private String titular;
        private double saldo;
        private String numeroCuenta;
        private String pin;
        private List<Transaccion> historial;

        public CuentaBancario() {
            historial = new ArrayList<>();
        }

        public String getTitular() {
            return titular;
        }

        public void setTitular(String titular) {
            this.titular = titular;
        }

        public double getSaldo() {
            return saldo;
        }

        public void setSaldo(double saldo) {
            this.saldo = saldo;
        }

        public String getNumeroCuenta() {
            return numeroCuenta;
        }

        public void setNumeroCuenta(String numeroCuenta) {
            this.numeroCuenta = numeroCuenta;
        }

        public String getPin() {
            return pin;
        }

        public void setPin(String pin) {
            this.pin = pin;
        }

        public List<Transaccion> getHistorial() {
            return historial;
        }

        public boolean verificarPin(String pinIngresado) {
            return this.pin.equals(pinIngresado);
        }

        public void depositarDinero(double monto) {
            if (monto > 0) {
                this.saldo += monto;
                historial.add(new Transaccion("Depósito", monto));
                System.out.println("Depósito exitoso. Nuevo saldo: " + this.saldo);
            } else {
                System.out.println("Monto inválido. Por favor, intente nuevamente.");
            }
        }

        public void retirarDinero(double monto) {
            if (monto > 0 && this.saldo >= monto) {
                this.saldo -= monto;
                historial.add(new Transaccion("Retiro", monto));
                System.out.println("Retiro exitoso. Nuevo saldo: " + this.saldo);
            } else if (monto > this.saldo) {
                System.out.println("Saldo insuficiente. No se puede retirar más de lo que hay en la cuenta.");
            } else {
                System.out.println("Monto inválido. Por favor, intente nuevamente.");
            }
        }

        @Override
        public String toString() {
            return "Titular: " + titular + ", Saldo: " + saldo + ", Número de cuenta: " + numeroCuenta;
        }
    }

