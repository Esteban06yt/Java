import java.util.*;

public class RegistroVentas {
    public static void main(String[] args) {

    }
}

class Venta {
    private final String codigoProducto;
    private final String nombreProducto;
    private final Integer cantidadVendida;
    private final Double valorTotal;

    public Venta(String codigoProducto, String nombreProducto, Integer cantidadVendida, Double valorTotal) {
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.cantidadVendida = cantidadVendida;
        this.valorTotal = valorTotal;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }
    public String getNombreProducto() {
        return nombreProducto;
    }
    public Integer getCantidadVendida() {
        return cantidadVendida;
    }
    public Double getValorTotal() {
        return valorTotal;
    }

    @Override
    public String toString() {
        return "Venta{" + "codigoProducto='" + codigoProducto + ", nombreProducto='" + nombreProducto + ", cantidadVendida=" + cantidadVendida + ", valorTotal=" + valorTotal + '}';
    }
}

class RegistroHashmap {
    private final HashMap<String, Venta> ventas = new HashMap<>();

    public void registrar(Venta venta) {
        ventas.put(venta.getCodigoProducto(), venta);
    }

    public Venta consultar(String codigo) {
        return ventas.get(codigo);
    }

    public void mostrarTodas() {
        System.out.println("\n[HashMap] Todas las ventas:");
        for (Venta venta : ventas.values()) {
            System.out.println("  " + venta);
        }
    }

    public void mostrarOrdenadoPorCodigo() {
        System.out.println("\n[HashMap] Ventas ordenadas por codigo:");
        List<Venta> listaVentas = new ArrayList<>(ventas.values());
        listaVentas.sort(Comparator.comparing(Venta::getCodigoProducto));
        for (Venta venta : listaVentas) {
            System.out.println("  " + venta);
        }
    }

    public void mostrarOrdenRegistro() {
        System.out.println("HashMap no preserva el orden de insercion.");
    }
}