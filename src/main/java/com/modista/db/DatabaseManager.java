package com.modista.db;

import com.modista.model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:modista.db";
    private static DatabaseManager instance;

    private DatabaseManager() {
        initializeDatabase();
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    private void initializeDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS clientas ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "nombre TEXT NOT NULL,"
            + "busto REAL NOT NULL,"
            + "cintura REAL NOT NULL,"
            + "cadera REAL NOT NULL,"
            + "talla TEXT NOT NULL,"
            + "fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
            + ")";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Error al inicializar la base de datos", e);
        }
    }

    public void guardarCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientas (nombre, busto, cintura, cadera, talla) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, cliente.getNombre());
            pstmt.setDouble(2, cliente.getBusto());
            pstmt.setDouble(3, cliente.getCintura());
            pstmt.setDouble(4, cliente.getCadera());
            pstmt.setString(5, cliente.getTalla());
            
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No se pudo guardar la cliente, no se creó ningún registro.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    cliente.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public Cliente buscarClientePorNombre(String nombre) throws SQLException {
        String sql = "SELECT id, nombre, busto, cintura, cadera, talla, fecha_registro " +
                    "FROM clientas WHERE nombre = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente(
                        rs.getString("nombre"),
                        rs.getDouble("busto"),
                        rs.getDouble("cintura"),
                        rs.getDouble("cadera")
                    );
                    cliente.setId(rs.getInt("id"));
                    cliente.setTalla(rs.getString("talla"));
                    return cliente;
                }
            }
        }
        return null;
    }

    public List<Cliente> buscarTodasLasClientes() throws SQLException {
        String sql = "SELECT id, nombre, busto, cintura, cadera, talla, fecha_registro " +
                    "FROM clientas ORDER BY fecha_registro DESC";
        List<Cliente> clientes = new ArrayList<>();
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getString("nombre"),
                    rs.getDouble("busto"),
                    rs.getDouble("cintura"),
                    rs.getDouble("cadera")
                );
                cliente.setId(rs.getInt("id"));
                cliente.setTalla(rs.getString("talla"));
                clientes.add(cliente);
            }
        }
        return clientes;
    }

    public void actualizarCliente(Cliente cliente) throws SQLException {
        String sql = "UPDATE clientas SET nombre = ?, busto = ?, cintura = ?, " +
                    "cadera = ?, talla = ? WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNombre());
            pstmt.setDouble(2, cliente.getBusto());
            pstmt.setDouble(3, cliente.getCintura());
            pstmt.setDouble(4, cliente.getCadera());
            pstmt.setString(5, cliente.getTalla());
            pstmt.setInt(6, cliente.getId());
            
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No se pudo actualizar la cliente, no se encontró el registro.");
            }
        }
    }
}
