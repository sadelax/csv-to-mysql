# csv-to-mysql
este script utiliza la biblioteca BasicDataSource de Apache Commons DBCP para configurar una conexión a la base de datos MySQL. Luego lee un archivo CSV y realiza inserciones en una tabla vacía de una base de datos ya existente.
si se produce algún error durante la ejecución, se imprimirá una traza de la excepción.
este script es útil para importar datos de un archivo CSV a una base de datos MySQL y se puede adaptar según los requisitos específicos del proyecto.

## requisitos previos
1. jdk 8 o superior.
2. drivers de mysql (incluidos en la carpeta resources) para agregarlas al classpath.
3. mysql server.

## cómo hacer que funcione
1. tener ya una base de datos creada, con al menos una tabla vacía con los encabezados correspondientes al csv.
2. dicha base de datos debe tener una columna id (int unsigned, NOT NULL, PRIMARY KEY, auto_increment).
3. importar proyecto a tu IDE favorito.
4. importar el fichero csv a main/resources.
5. editar las siguientes líneas el fichero java CsvToMySql.java:
	```
	private static final String CSV_FILE = "resources/iba-cocktails-ingredients-web.csv"; // ruta del fichero csv a convertir
	private static final String TABLE_NAME = "cocktails1fn"; // nombre de la tabla vacía de la base de datos
	```
	```
	public static void main(String[] args) throws IOException, SQLException {
		DataSource ds;
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName("com.mysql.cj.jdbc.Driver");	// este es el driver de mysql
		bds.setUrl("jdbc:mysql://localhost/picky_cocktail_iba_db"); // "jdbc:mysql://localhost/nombre-base-de-datos"
		bds.setUsername("root"); // usuario de mysql
		bds.setPassword("root"); // contraseña
		ds = bds;
	```
6. editar de manera proporcional el nombre de las columnas, como interrogaciones, como preparedStatement.setString.
	Si tu csv tiene 4 columnas, añadir el encabezado de cada columna, 4 interrogaciones, y 4 preparedStatement con su correspondiente valor ordenado.
	```
	while ((line = br.readLine()) != null) {
		String[] values = line.split(",");
		String sql = "INSERT INTO " + TABLE_NAME + " (columna1, columna2) VALUES (?, ?)";
		preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, values[0]);
		preparedStatement.setString(2, values[1]);
		preparedStatement.executeUpdate();
	```
7. ejecutar y voilá. tu tabla nueva está completada en tu base de datos.
