import java.util.ArrayList;
import java.util.List;
public class Banco {
        private List<CuentaBancario> cuentas;

        public Banco() {
            this.cuentas = new ArrayList<>();
        }

        public void agregarCuenta(CuentaBancario cuenta) {
            cuentas.add(cuenta);
        }

        public boolean eliminarCuenta(String numeroCuenta) {
            return cuentas.removeIf(cuenta -> cuenta.getNumeroCuenta().equalsIgnoreCase(numeroCuenta));
        }

        public CuentaBancario   buscarPorNumero(String numeroCuenta) {
            for (CuentaBancario cuenta : cuentas) {
                if (cuenta.getNumeroCuenta().equalsIgnoreCase(numeroCuenta)) {
                    return cuenta;
                }
            }
            return null;
        }

        public List<CuentaBancario> buscarPorTitular(String nombreTitular) {
            List<CuentaBancario> resultado = new ArrayList<>();
            for (CuentaBancario cuenta : cuentas) {
                if (cuenta.getTitular().equalsIgnoreCase(nombreTitular)) {
                    resultado.add(cuenta);
                }
            }
            return resultado;
        }

        public List<CuentaBancario> getCuentas() {
            return cuentas;
        }
    }
