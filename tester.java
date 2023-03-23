public class tester {
    public static void main(String[] args) {
        Servidor server = new Servidor(2021);
        try {
            server.Host();
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
