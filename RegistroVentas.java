import java.util.*;

public class RegistroVentas {
    private static final Venta[] VENTAS_DIA = {
        new Venta("P003", "Monitor Samsung 27\"", 2, 699.98),
        new Venta("P001", "Laptop HP 15\"", 1, 1299.99),
        new Venta("P007", "Teclado Mecanico RGB", 5, 374.95),
        new Venta("P002", "Mouse Inalambrico", 8, 239.92),
        new Venta("P009", "Audifonos Sony", 3, 899.97),
        new Venta("P005", "Webcam Full HD", 4, 279.96),
        new Venta("P004", "Hub USB-C 7 puertos", 6, 179.94),
        new Venta("P006", "SSD Externo 1TB", 2, 159.98),
    };

    public static void main(String[] args) {
        encabezado("REGISTRO DE VENTAS DIARIAS");
        System.out.println("Comparacion: HashMap | LinkedHashMap | TreeMap\n");

        RegistroHashMap regHash = new RegistroHashMap();
        RegistroLinkedHashMap regLinked = new RegistroLinkedHashMap();
        RegistroTreeMap regTree = new RegistroTreeMap();

        System.out.println("Ventas registradas en el siguiente orden:");
        for (Venta venta : VENTAS_DIA) {
            System.out.println("    -> Codigo: " + venta.getCodigoProducto() + " | " + venta.getNombreProducto());
            regHash.registrar(venta);
            regLinked.registrar(venta);
            regTree.registrar(venta);
        }

        encabezado("1. CONSULTA POR CoDIGO");
        String[] consultas = {"P005", "P001", "P099"};
        for (String codigo : consultas) {
            System.out.println("\nBuscando codigo: " + codigo);

            Venta hash = regHash.consultar(codigo);
            System.out.println("HashMap -> " + (hash != null ? hash : "NO ENCONTRADO"));

            Venta linked = regLinked.consultar(codigo);
            System.out.println("LinkedHashMap -> " + (linked != null ? linked : "NO ENCONTRADO"));

            Venta tree = regTree.consultar(codigo);
            System.out.println("TreeMap -> " + (tree != null ? tree : "NO ENCONTRADO"));
        }

        encabezado("2. MOSTRAR TODAS LAS VENTAS");
        regHash.mostrarTodas();
        regLinked.mostrarTodas();
        regTree.mostrarTodas();

        encabezado("3. VENTAS ORDENADAS POR CoDIGO");
        regHash.mostrarOrdenadoPorCodigo();
        regLinked.mostrarOrdenadoPorCodigo();
        regTree.mostrarOrdenadoPorCodigo();

        encabezado("4. VENTAS EN ORDEN DE REGISTRO");
        regHash.mostrarOrdenRegistro();
        regLinked.mostrarOrdenRegistro();
        regTree.mostrarOrdenRegistro();
    }

    static void encabezado(String titulo) {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("  " + titulo);
        System.out.println("=".repeat(70));
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

class RegistroHashMap {
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
        System.out.println("\n[HashMap] Orden de registro: NO disponible nativamente.");
        System.out.println("HashMap no preserva el orden de insercion.");
    }
}

class RegistroLinkedHashMap {
    private final LinkedHashMap<String, Venta> ventas = new LinkedHashMap<>();

    public void registrar(Venta venta) {
        ventas.put(venta.getCodigoProducto(), venta);
    }

    public Venta consultar(String codigoProducto) {
        return ventas.get(codigoProducto);
    }

    public void mostrarTodas() {
        System.out.println("\n[LinkedHashMap] Todas las ventas (orden de insercion):");
        for (Venta venta : ventas.values()) {
            System.out.println("  " + venta);
        }
    }

    public void mostrarOrdenadoPorCodigo() {
        System.out.println("\n[LinkedHashMap] Ventas ordenadas por codigo:");
        List<Venta> lista = new ArrayList<>(ventas.values());
        lista.sort(Comparator.comparing(Venta::getCodigoProducto));
        for (Venta venta : lista) {
            System.out.println("  " + venta);
        }
    }

    public void mostrarOrdenRegistro() {
        System.out.println("\n[LinkedHashMap] Orden exacto de registro:");
        for (Venta venta : ventas.values()) {
            System.out.println("  " + venta);
        }
    }
}

class RegistroTreeMap {
    private final TreeMap<String, Venta> ventas = new TreeMap<>();

    public void registrar(Venta venta) {
        ventas.put(venta.getCodigoProducto(), venta);
    }

    public Venta consultar(String codigoProducto) {
        return ventas.get(codigoProducto);
    }

    public void mostrarTodas() {
        System.out.println("\n[TreeMap] Todas las ventas:");
        for (Venta venta : ventas.values()) {
            System.out.println("  " + venta);
        }
    }

    public void mostrarOrdenadoPorCodigo() {
        System.out.println("\n[TreeMap] Ventas ordenadas por codigo:");
        for (Venta venta : ventas.values()) {
            System.out.println("  " + venta);
        }
    }

    public void mostrarOrdenRegistro() {
        System.out.println("\n[TreeMap] Orden de registro: NO disponible nativamente.");
        System.out.println("TreeMap no preserva el orden de insercion.");
    }
}