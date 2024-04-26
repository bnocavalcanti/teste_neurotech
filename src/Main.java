import com.google.gson.Gson;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        // Configurações de conexão com o banco de dados
        String url = "jdbc:mysql://localhost:3306/dbNeurotech";
        String username = "root";
        String password = "";



        //Entrada de dados
        Scanner scanner = new Scanner(System.in);

        try {
            //Recebe o cep do usuário
            System.out.println("Digite o cep desejado: ");
            String cep = scanner.nextLine();

            //valida quandidade de caracteres do cep
            if (!cep.matches("[0-9]{8}")){
                System.out.println("Apenas números.");

            }
            else{
                //consumindo API
                var client = HttpClient.newHttpClient();

                var request = HttpRequest.newBuilder(
                                URI.create("https://brasilapi.com.br/api/cep/v1/" + cep)
                        ).header("accept", "application/json")
                        .build();

                var response = client.send(request, HttpResponse.BodyHandlers.ofString());

                //validando resultado
                int statusCode = response.statusCode();
                if(statusCode == 200) {
                    Gson g = new Gson();
                    CepResponse cepResponse = g.fromJson(response.body(), CepResponse.class);
                    System.out.println("\n ******** \n");
                    System.out.println("Cep: " + cepResponse.cep);
                    System.out.println("UF: " + cepResponse.state);
                    System.out.println("Cidade: " + cepResponse.city);
                    System.out.println("Vizinhança: " + cepResponse.neighborhood);
                    System.out.println("Rua: " + cepResponse.street + "\n");

                    try {
                        //estabelece conexão com o banco
                        Class.forName("com.mysql.cj.jdbc.Driver");

                        Connection connection = DriverManager.getConnection(url, username, password);

                        Statement statement = connection.createStatement();



                        // Usei PreparedStatement para inserir os valores reais

                        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Address (cep, state, city, neighborhood, street) VALUES ('"+cepResponse.cep+"', '"+cepResponse.state+"', '"+cepResponse.city+"', '"+cepResponse.neighborhood+"', '"+cepResponse.street+"')");

                        // Executa a inserção
                        int rowsAffected = preparedStatement.executeUpdate();

                        // verifica se a inserção foi bem-sucedida
                        if (rowsAffected > 0) {
                            System.out.println("Inserção realizada com sucesso.\n"+ "\n ******** \n");
                        } else {
                            System.out.println("Nenhuma linha afetada pela inserção.");
                        }

                        // imprime o resultado inserido do banco
                        ResultSet resultSet = statement.executeQuery("SELECT * FROM Address");
                        System.out.println("---Dados armazenados no banco--- \n");
                        while (resultSet.next()) {

                            System.out.println("Cep: " +resultSet.getInt(2) + " | UF: " + resultSet.getString(3)+ " | Cidade: " + resultSet.getString(4)  +
                                    " | Vizinhança: " + resultSet.getString(5) + " | Rua: " + resultSet.getString(6));
                            }

                        connection.close();
                    }
                    //caso haja algum erro com o banco
                    catch (Exception e) {
                        System.out.println("Ocorreu um erro durante a inserção no banco de dados: " + e.getMessage());
                    }

                    //caso haja algum erro na digitação do cep
                } else {
                    System.out.println("Houve algum erro na execução, tente novamente.");
                }

            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        scanner.close();
    }

    //objeto do retorno da API
    public class CepResponse{
        public String cep;
        public String state;
        public String city;
        public String neighborhood;
        public String street;
        public String service;

    }

}

