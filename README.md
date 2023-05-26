# csv-to-mysql
a Java script to import CSV data into MySQL database.

## cómo hacer que funcione
1. importar proyecto a tu IDE favorito.
2. importar el fichero csv a main/resources.
3. editar las siguientes líneas el fichero java CsvToMySql.java:
'''
private static final String CSV_FILE = "/resources/iba-cocktails-ingredients-web.csv"; // ruta del fichero csv a convertir
private static final String TABLE_NAME = "cocktails1fn"; // nombre de la tabla de la base de datos
'''
'''
public static void main(String[] args) throws IOException, SQLException {
		DataSource ds;
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName("com.mysql.cj.jdbc.Driver");	// este es el driver de mysql
		bds.setUrl("jdbc:mysql://localhost/picky_cocktail_iba_db"); // "jdbc:mysql://localhost/nombre-base-de-datos"
		bds.setUsername("root"); // usuario de mysql
		bds.setPassword("root"); // contraseña
		ds = bds;
'''
