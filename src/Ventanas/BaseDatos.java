package Ventanas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class BaseDatos {



		
		private static String URL="jdbc:sqlite:baseDatosProyecto.bd";
		private static Connection connect() throws SQLException {
			return DriverManager.getConnection(URL);
		}
		public static void crearTablas() {
			try {
				Connection conn=connect();
				
				System.out.println("Connection to SQLite has been stablished");
				Statement stmnt=conn.createStatement();
				stmnt.execute("PRAGMA foreign_keys = ON");
				
				
				String sqlUsuario="CREATE TABLE IF NOT EXISTS usuario (" + 
					    "id_usuario INTEGER PRIMARY KEY AUTOINCREMENT," +
					    "nombre TEXT NOT NULL,"+
					    "apellidos TEXT NOT NULL,"+
					    "usuario TEXT NOT NULL,"+
					    "contrasena TEXT NOT NULL)";

				String sqlDeporte="CREATE TABLE IF NOT EXISTS deporte (" +
					    "id_deporte INTEGER NOT NULL PRIMARY KEY," +
					    "tipo TEXT NOT NULL CHECK(tipo IN ('FUTBOL','BALONCESTO','GIMNASIA','NATACION','RUGBY','ATLETISMO')))";

				
		
				
				String sqlAlojamiento="CREATE TABLE IF NOT EXISTS alojamiento ("+
										"id_alojamiento INTEGER NOT NULL PRIMARY KEY,"+
										"nombre_edificio TEXT NOT NULL)";
				
				String sqlHabitacion="CREATE TABLE IF NOT EXISTS habitacion (" +
										"id_habitacion INTEGER NOT NULL PRIMARY KEY,"+
										"piso INTEGER NOT NULL,"+
										"num_pasillo INT NOT NULL,"+
										"num_puerta INT NOT NULL CHECK(num_puerta<=100),"+
										"ocupado INTEGER NOT NULL CHECK(ocupado IN (0,1)),"+
										"id_alojamiento INT NOT NULL,"+
										"FOREIGN KEY (id_alojamiento) REFERENCES alojamiento(id_alojamiento) ON DELETE CASCADE)";
				
				
				String sqlEntrenador="CREATE TABLE IF NOT EXISTS entrenador ("+
										"id_entrenador INTEGER NOT NULL PRIMARY KEY,"+
										"id_usuario INT NOT NULL,"+
										"id_deporte INT NOT NULL,"+
										"id_habitacion INT NOT NULL,"+
										"FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE,"+
										"FOREIGN KEY (id_deporte) REFERENCES deporte(id_deporte) ON DELETE CASCADE,"+
										"FOREIGN KEY (id_habitacion) REFERENCES habitacion(id_habitacion) ON DELETE CASCADE)";
				String sqlLugarAct="CREATE TABLE IF NOT EXISTS lugar_act ("+
						"id_lugar INTEGER NOT NULL PRIMARY KEY,"+
						"nombre_lugar TEXT NOT NULL,"+
						"descripcion TEXT NOT NULL)";
				
				String sqlActividad = "CREATE TABLE IF NOT EXISTS actividad (" +
					    "id_actividad INTEGER NOT NULL PRIMARY KEY," +
					    "id_entrenador INT NOT NULL," +
					    "id_lugar INT NOT NULL," +

					    "fecha_act DATETIME NOT NULL," +
					    "nombre_act TEXT NOT NULL" +

					    "descripcion TEXT NOT NULL"+
					    "fecha_act DATE NOT NULL," +

					    "FOREIGN KEY (id_entrenador) REFERENCES entrenador(id_entrenador) ON DELETE CASCADE," +
					    "FOREIGN KEY (id_lugar) REFERENCES lugar_act(id_lugar) ON DELETE CASCADE)";
				
				String sqlDeportista="CREATE TABLE IF NOT EXISTS deportista ("+
						"id_deportista INTEGER PRIMARY KEY NOT NULL,"+
						"id_usuario INT NOT NULL,"+
	                    "id_deporte INT NOT NULL,"+
	                    "id_habitacion INT NOT NULL,"+
	                    "FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE,"+
	                    "FOREIGN KEY (id_actividad) REFERENCES actividad(id_actividad) ON DELETE CASCADE,"+
	                    "FOREIGN KEY (id_deporte) REFERENCES deporte(id_deporte) ON DELETE CASCADE,"+
	                    "FOREIGN KEY (id_habitacion) REFERENCES habitacion(id_habitacion) ON DELETE CASCADE)";
						
				String sqlFisioterapeuta = "CREATE TABLE IF NOT EXISTS fisioterapeuta (" +
					    "id_fisio INTEGER NOT NULL PRIMARY KEY," +
					    "id_usuario INT NOT NULL," +
					    "id_habitacion INT NOT NULL," +
					    "FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE," +
					    "FOREIGN KEY (id_habitacion) REFERENCES habitacion(id_habitacion) ON DELETE CASCADE)";

				String sqlAdministrador = "CREATE TABLE IF NOT EXISTS administrador (" +
					    "id_admin INTEGER NOT NULL PRIMARY KEY," +
					    "id_usuario INT NOT NULL," +
					    "FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE)";
				
			
				String sqlCita = "CREATE TABLE IF NOT EXISTS cita (" +
					    "id_cita INTEGER NOT NULL PRIMARY KEY," +
					    "id_fisio INT NOT NULL," +
					    "id_deportista INT NOT NULL," +
					    "fecha_cita DATETIME NOT NULL," +
					    "FOREIGN KEY (id_fisio) REFERENCES fisioterapeuta(id_fisio) ON DELETE CASCADE," +
					    "FOREIGN KEY (id_deportista) REFERENCES deportista(id_deportista) ON DELETE CASCADE)";
				
				String sqlHistoria = "CREATE TABLE IF NOT EXISTS historia (" +
					    "id_historia INTEGER NOT NULL PRIMARY KEY," +
					    "descripcion TEXT NOT NULL)";

				
				stmnt.executeUpdate(sqlUsuario);
				stmnt.execute("INSERT OR IGNORE INTO usuario (id_usuario,nombre, apellidos, usuario, contrasena) "
						+ "VALUES (1, 'Luka', 'Bengoetxea Ormazabal', 'luka.bengorma', 'lukabengo1')");
				stmnt.execute("INSERT OR IGNORE INTO usuario (id_usuario,nombre, apellidos, usuario, contrasena) "
						+ "VALUES (2, 'Maddi', 'Aranbarri Zelaia', 'maddi.aranbarri', 'maaranzel')");
				stmnt.execute("INSERT OR IGNORE INTO usuario (id_usuario,nombre, apellidos, usuario, contrasena) "
						+ "VALUES (3, 'Beñat', 'Odriozola Odriozola', 'benatodri2', 'odriodri')");
				stmnt.execute("INSERT OR IGNORE INTO usuario (id_usuario,nombre, apellidos, usuario, contrasena) "
						+ "VALUES (4, 'Maria', 'Larrañaga Aperribai', 'maria.larra', 'marilar')");
				stmnt.execute("INSERT OR IGNORE INTO usuario (id_usuario,nombre, apellidos, usuario, contrasena) "
				        + "VALUES (5, 'Ane', 'Etxebarria Zubizarreta', 'ane.etxezubi', 'ane123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario (id_usuario,nombre, apellidos, usuario, contrasena) "
				        + "VALUES (6, 'Iker', 'Garmendia Landa', 'iker.garmenda', 'ikerpass')");

				// Más usuarios
				stmnt.execute("INSERT OR IGNORE INTO usuario (id_usuario,nombre, apellidos, usuario, contrasena) "
				        + "VALUES (7, 'Naiara', 'Zubiri Etxeberria', 'naiara.zubiri', 'naiara123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario (id_usuario,nombre, apellidos, usuario, contrasena) "
				        + "VALUES (8, 'Oier', 'Ibarra Aguirre', 'oier.ibarra', 'oierpass')");
				stmnt.execute("INSERT OR IGNORE INTO usuario (id_usuario,nombre, apellidos, usuario, contrasena) "
				        + "VALUES (9, 'Leire', 'Sarasola Lertxundi', 'leire.sarasola', 'leire123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario (id_usuario,nombre, apellidos, usuario, contrasena) "
				        + "VALUES (10, 'Asier', 'Mendizabal Arrieta', 'asier.mendizabal', 'asierpass')");
				stmnt.execute("INSERT OR IGNORE INTO usuario (id_usuario,nombre, apellidos, usuario, contrasena) "
				        + "VALUES (11, 'Irati', 'Gonzalez Etxebarria', 'irati.gonzalez', 'irati123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario (id_usuario,nombre, apellidos, usuario, contrasena) "
				        + "VALUES (12, 'Jon', 'Urrutia Zelaia', 'jon.urrutia', 'jonpass')");
				stmnt.execute("INSERT OR IGNORE INTO usuario (id_usuario,nombre, apellidos, usuario, contrasena) "
				        + "VALUES (13, 'Aiora', 'Lizarazu Aranburu', 'aiora.lizarazu', 'aiora123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario (id_usuario,nombre, apellidos, usuario, contrasena) "
				        + "VALUES (14, 'Markel', 'Etxebeste Goikoetxea', 'markel.etxebeste', 'markelpass')");
				stmnt.execute("INSERT OR IGNORE INTO usuario (id_usuario,nombre, apellidos, usuario, contrasena) "
				        + "VALUES (15, 'Maite', 'Uranga Otxoa', 'maite.uranga', 'maite123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario (id_usuario,nombre, apellidos, usuario, contrasena) "
				        + "VALUES (16, 'Aritz', 'Arrieta Zubia', 'aritz.arrieta', 'aritzpass')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (17, 'Ander', 'Garcia Lopez', 'ander.garcia', 'ander123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (18, 'Nerea', 'Martinez Ruiz', 'nerea.martinez', 'nerea123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (19, 'Mikel', 'Sanchez Diaz', 'mikel.sanchez', 'mikel123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (20, 'Irune', 'Gomez Fernandez', 'irune.gomez', 'irune123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (21, 'Iker', 'Lopez Alonso', 'iker.lopez', 'iker123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (22, 'Amaia', 'Diaz Romero', 'amaia.diaz', 'amaia123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (23, 'Javier', 'Fernandez Morales', 'javier.fernandez', 'javier123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (24, 'Sara', 'Alonso Perez', 'sara.alonso', 'sara123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (25, 'Unai', 'Romero Jimenez', 'unai.romero', 'unai123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (26, 'Eider', 'Morales Sanchez', 'eider.morales', 'eider123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (27, 'Pablo', 'Perez Gonzalez', 'pablo.perez', 'pablo123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (28, 'Leire', 'Jimenez Lopez', 'leire.jimenez', 'leire123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (29, 'Hugo', 'Gonzalez Diaz', 'hugo.gonzalez', 'hugo123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (30, 'Ane', 'Ruiz Fernandez', 'ane.ruiz', 'ane123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (31, 'Jon', 'Martinez Alonso', 'jon.martinez', 'jon123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (32, 'Maialen', 'Diaz Perez', 'maialen.diaz', 'maialen123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (33, 'Iñigo', 'Sanchez Romero', 'inigo.sanchez', 'inigo123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (34, 'Irati', 'Gomez Morales', 'irati.gomez', 'irati123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (35, 'Asier', 'Lopez Perez', 'asier.lopez', 'asier123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (36, 'Oihana', 'Romero Fernandez', 'oihana.romero', 'oihana123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (37, 'Aitor', 'Fernandez Lopez', 'aitor.fernandez', 'aitor123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (38, 'Ona', 'Martinez Diaz', 'ona.martinez', 'ona123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (39, 'Iñaki', 'Gomez Ruiz', 'inaki.gomez', 'inaki123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (40, 'Nahia', 'Lopez Fernandez', 'nahia.lopez', 'nahia123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (41, 'Asier', 'Diaz Alonso', 'asier.diaz', 'asier123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (42, 'Leire', 'Romero Sanchez', 'leire.romero', 'leire123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (43, 'Hugo', 'Martinez Fernandez', 'hugo.martinez', 'hugo123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (44, 'Amaia', 'Perez Gonzalez', 'amaia.perez', 'amaia123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (45, 'Jon', 'Jimenez Lopez', 'jon.jimenez', 'jon123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (46, 'Iker', 'Sanchez Ruiz', 'iker.sanchez', 'iker123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (47, 'Irune', 'Fernandez Diaz', 'irune.fernandez', 'irune123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (48, 'Mikel', 'Gomez Perez', 'mikel.gomez', 'mikel123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (49, 'Nerea', 'Lopez Morales', 'nerea.lopez', 'nerea123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (50, 'Unai', 'Romero Sanchez', 'unai.romero', 'unai123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (51, 'Ane', 'Perez Fernandez', 'ane.perez', 'ane123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (52, 'Pablo', 'Diaz Gonzalez', 'pablo.diaz', 'pablo123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (53, 'Maialen', 'Sanchez Lopez', 'maialen.sanchez', 'maialen123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (54, 'Iñigo', 'Fernandez Ruiz', 'inigo.fernandez', 'inigo123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (55, 'Irati', 'Gomez Alonso', 'irati.gomez', 'irati123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (56, 'Asier', 'Lopez Diaz', 'asier.lopez', 'asier123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (57, 'Oihana', 'Romero Fernandez', 'oihana.romero', 'oihana123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (58, 'Ander', 'Fernandez Ruiz', 'ander.fernandez', 'ander123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (59, 'Nerea', 'Martinez Lopez', 'nerea.martinez', 'nerea123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (60, 'Mikel', 'Sanchez Fernandez', 'mikel.sanchez', 'mikel123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (61, 'Irune', 'Gomez Diaz', 'irune.gomez', 'irune123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (62, 'Iker', 'Lopez Perez', 'iker.lopez', 'iker123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (63, 'Amaia', 'Diaz Morales', 'amaia.diaz', 'amaia123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (64, 'Javier', 'Fernandez Lopez', 'javier.fernandez', 'javier123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (65, 'Sara', 'Alonso Ruiz', 'sara.alonso', 'sara123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (66, 'Unai', 'Romero Diaz', 'unai.romero', 'unai123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (67, 'Eider', 'Morales Lopez', 'eider.morales', 'eider123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (68, 'Pablo', 'Perez Fernandez', 'pablo.perez', 'pablo123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (69, 'Leire', 'Jimenez Diaz', 'leire.jimenez', 'leire123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (70, 'Hugo', 'Gonzalez Ruiz', 'hugo.gonzalez', 'hugo123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (71, 'Ane', 'Ruiz Lopez', 'ane.ruiz', 'ane123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (72, 'Jon', 'Martinez Fernandez', 'jon.martinez', 'jon123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (73, 'Maialen', 'Diaz Perez', 'maialen.diaz', 'maialen123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (74, 'Iñigo', 'Sanchez Lopez', 'inigo.sanchez', 'inigo123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (75, 'Irati', 'Gomez Fernandez', 'irati.gomez', 'irati123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (76, 'Asier', 'Lopez Diaz', 'asier.lopez', 'asier123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (77, 'Oihana', 'Romero Ruiz', 'oihana.romero', 'oihana123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (78, 'Ander', 'Fernandez Diaz', 'ander.fernandez', 'ander123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (79, 'Nerea', 'Martinez Lopez', 'nerea.martinez', 'nerea123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (80, 'Mikel', 'Sanchez Ruiz', 'mikel.sanchez', 'mikel123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (81, 'Irune', 'Gomez Lopez', 'irune.gomez', 'irune123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (82, 'Iker', 'Lopez Fernandez', 'iker.lopez', 'iker123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (83, 'Amaia', 'Diaz Ruiz', 'amaia.diaz', 'amaia123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (84, 'Javier', 'Fernandez Gonzalez', 'javier.fernandez', 'javier123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (85, 'Sara', 'Alonso Lopez', 'sara.alonso', 'sara123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (86, 'Unai', 'Romero Fernandez', 'unai.romero', 'unai123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (87, 'Eider', 'Morales Ruiz', 'eider.morales', 'eider123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (88, 'Pablo', 'Perez Lopez', 'pablo.perez', 'pablo123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (89, 'Leire', 'Jimenez Ruiz', 'leire.jimenez', 'leire123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (90, 'Hugo', 'Gonzalez Lopez', 'hugo.gonzalez', 'hugo123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (91, 'Ane', 'Ruiz Fernandez', 'ane.ruiz', 'ane123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (92, 'Jon', 'Martinez Diaz', 'jon.martinez', 'jon123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (93, 'Maialen', 'Diaz Lopez', 'maialen.diaz', 'maialen123')");
				//OCUPADAS HASTA AQUI 
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (94, 'Iñigo', 'Sanchez Fernandez', 'inigo.sanchez', 'inigo123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (95, 'Irati', 'Gomez Ruiz', 'irati.gomez', 'irati123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (96, 'Asier', 'Lopez Fernandez', 'asier.lopez', 'asier123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (97, 'Oihana', 'Romero Diaz', 'oihana.romero', 'oihana123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (98, 'Ander', 'Fernandez Lopez', 'ander.fernandez', 'ander123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (99, 'Nerea', 'Martinez Ruiz', 'nerea.martinez', 'nerea123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (100, 'Mikel', 'Sanchez Diaz', 'mikel.sanchez', 'mikel123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (101, 'Irune', 'Gomez Fernandez', 'irune.gomez', 'irune123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (102, 'Iker', 'Lopez Ruiz', 'iker.lopez', 'iker123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (103, 'Amaia', 'Diaz Fernandez', 'amaia.diaz', 'amaia123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (104, 'Javier', 'Fernandez Lopez', 'javier.fernandez', 'javier123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (105, 'Sara', 'Alonso Ruiz', 'sara.alonso', 'sara123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (106, 'Unai', 'Romero Lopez', 'unai.romero', 'unai123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (107, 'Eider', 'Morales Fernandez', 'eider.morales', 'eider123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (108, 'Pablo', 'Perez Ruiz', 'pablo.perez', 'pablo123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (109, 'Leire', 'Jimenez Lopez', 'leire.jimenez', 'leire123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (110, 'Hugo', 'Gonzalez Fernandez', 'hugo.gonzalez', 'hugo123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (111, 'Ane', 'Ruiz Lopez', 'ane.ruiz', 'ane123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (112, 'Jon', 'Martinez Fernandez', 'jon.martinez', 'jon123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (113, 'Maialen', 'Diaz Ruiz', 'maialen.diaz', 'maialen123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (114, 'Iñigo', 'Sanchez Lopez', 'inigo.sanchez', 'inigo123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (115, 'Irati', 'Gomez Fernandez', 'irati.gomez', 'irati123')");
				stmnt.execute("INSERT OR IGNORE INTO usuario VALUES (116, 'Asier', 'Lopez Ruiz', 'asier.lopez', 'asier123')");

				
				
				
				stmnt.executeUpdate(sqlDeporte);
				stmnt.execute("INSERT OR IGNORE INTO deporte(id_deporte, tipo) VALUES (1,'FUTBOL')");
				stmnt.execute("INSERT OR IGNORE INTO deporte(id_deporte, tipo) VALUES (2,'BALONCESTO')");
				stmnt.execute("INSERT OR IGNORE INTO deporte(id_deporte, tipo) VALUES (3,'GIMNASIA')");
				stmnt.execute("INSERT OR IGNORE INTO deporte(id_deporte, tipo) VALUES (4,'NATACION')");
				stmnt.execute("INSERT OR IGNORE INTO deporte(id_deporte, tipo) VALUES (5,'RUGBY')");
				stmnt.execute("INSERT OR IGNORE INTO deporte(id_deporte, tipo) VALUES (6,'ATLETISMO')");
				
				stmnt.executeUpdate(sqlAlojamiento);
				stmnt.execute("INSERT OR IGNORE INTO alojamiento (id_alojamiento, nombre_edificio) "
						+ "VALUES (1, 'Deportistas')");
				stmnt.execute("INSERT OR IGNORE INTO alojamiento (id_alojamiento, nombre_edificio) "
						+ "VALUES (2, 'Trabajadores')");

				stmnt.executeUpdate(sqlHabitacion);
				
				//EDIFICIO DEPORTISTAS
				//tiene 5 pisos, cada piso 2 pasillos y cada pasillo 10 habitaciones
				// Piso 1 - Pasillo 1
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (1,1,1,1,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (2,1,1,2,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (3,1,1,3,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (4,1,1,4,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (5,1,1,5,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (6,1,1,6,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (7,1,1,7,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (8,1,1,8,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (9,1,1,9,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (10,1,1,10,1,1)");

				// Piso 1 - Pasillo 2
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (11,1,2,11,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (12,1,2,12,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (13,1,2,13,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (14,1,2,14,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (15,1,2,15,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (16,1,2,16,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (17,1,2,17,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (18,1,2,18,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (19,1,2,19,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (20,1,2,20,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (11,1,2,1,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (12,1,2,2,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (13,1,2,3,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (14,1,2,4,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (15,1,2,5,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (16,1,2,6,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (17,1,2,7,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (18,1,2,8,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (19,1,2,9,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (20,1,2,10,1,1)");

				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (21,2,1,21,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (22,2,1,22,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (23,2,1,23,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (24,2,1,24,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (25,2,1,25,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (26,2,1,26,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (27,2,1,27,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (28,2,1,28,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (29,2,1,29,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (30,2,1,30,0,1)");

				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (21,2,1,1,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (22,2,1,2,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (23,2,1,3,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (24,2,1,4,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (25,2,1,5,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (26,2,1,6,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (27,2,1,7,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (28,2,1,8,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (29,2,1,9,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (30,2,1,10,1,1)");

				// Piso 2 - Pasillo 2

				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (31,2,2,31,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (32,2,2,32,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (33,2,2,33,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (34,2,2,34,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (35,2,2,35,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (36,2,2,36,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (37,2,2,37,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (38,2,2,38,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (39,2,2,39,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (40,2,2,40,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (31,2,2,1,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (32,2,2,2,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (33,2,2,3,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (34,2,2,4,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (35,2,2,5,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (36,2,2,6,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (37,2,2,7,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (38,2,2,8,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (39,2,2,9,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (40,2,2,10,1,1)");

				// Piso 3 - Pasillo 1
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (41,3,1,41,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (42,3,1,42,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (43,3,1,43,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (44,3,1,44,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (45,3,1,45,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (46,3,1,46,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (47,3,1,47,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (48,3,1,48,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (49,3,1,49,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (50,3,1,50,0,1)");

				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (41,3,1,1,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (42,3,1,2,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (43,3,1,3,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (44,3,1,4,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (45,3,1,5,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (46,3,1,6,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (47,3,1,7,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (48,3,1,8,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (49,3,1,9,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (50,3,1,10,1,1)");

				// Piso 3 - Pasillo 2
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (51,3,2,51,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (52,3,2,52,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (53,3,2,53,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (54,3,2,54,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (55,3,2,55,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (56,3,2,56,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (57,3,2,57,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (58,3,2,58,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (59,3,2,59,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (60,3,2,60,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (51,3,2,1,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (52,3,2,2,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (53,3,2,3,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (54,3,2,4,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (55,3,2,5,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (56,3,2,6,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (57,3,2,7,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (58,3,2,8,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (59,3,2,9,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (60,3,2,10,1,1)");

				// Piso 4 - Pasillo 1
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (61,4,1,61,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (62,4,1,62,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (63,4,1,63,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (64,4,1,64,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (65,4,1,65,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (66,4,1,66,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (67,4,1,67,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (68,4,1,68,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (69,4,1,69,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (70,4,1,70,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (61,4,1,1,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (62,4,1,2,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (63,4,1,3,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (64,4,1,4,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (65,4,1,5,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (66,4,1,6,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (67,4,1,7,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (68,4,1,8,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (69,4,1,9,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (70,4,1,10,1,1)");

				// Piso 4 - Pasillo 2
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (71,4,2,71,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (72,4,2,72,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (73,4,2,73,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (74,4,2,74,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (75,4,2,75,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (76,4,2,76,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (77,4,2,77,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (78,4,2,78,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (79,4,2,79,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (80,4,2,80,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (71,4,2,1,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (72,4,2,2,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (73,4,2,3,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (74,4,2,4,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (75,4,2,5,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (76,4,2,6,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (77,4,2,7,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (78,4,2,8,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (79,4,2,9,1,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (80,4,2,10,1,1)");

				// Piso 5 - Pasillo 1
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (81,5,1,81,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (82,5,1,82,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (83,5,1,83,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (84,5,1,84,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (85,5,1,85,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (86,5,1,86,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (87,5,1,87,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (88,5,1,88,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (89,5,1,89,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (90,5,1,90,0,1)");

				// Piso 5 - Pasillo 2
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (91,5,2,91,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (92,5,2,92,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (93,5,2,93,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (94,5,2,94,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (95,5,2,95,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (96,5,2,96,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (97,5,2,97,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (98,5,2,98,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (99,5,2,99,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (100,5,2,100,0,1)");
				
				//Piso 6 - Pasillo 1
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (101,6,1,101,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (102,6,1,102,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (103,6,1,103,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (104,6,1,104,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (105,6,1,105,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (106,6,1,106,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (107,6,1,107,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (108,6,1,108,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (109,6,1,109,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (110,6,1,110,0,1)");
				
				//Piso 6 - Pasillo 2
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (111,6,2,111,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (112,6,2,112,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (113,6,2,113,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (114,6,2,114,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (115,6,2,115,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (116,6,2,116,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (117,6,2,117,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (118,6,2,118,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (119,6,2,119,0,1)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (120,6,2,120,0,1)");
				
				//EDIFICIO ENTRENADORES
				//este edificio tiene 2 pisos
				// Piso 1 - Pasillo 1
				
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (121,1,1,121,1,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (122,1,1,122,1,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (123,1,1,123,1,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (124,1,1,124,1,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (125,1,1,125,1,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (126,1,1,126,1,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (127,1,1,127,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (128,1,1,128,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (129,1,1,129,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (130,1,1,130,0,2)");

				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (101,1,1,1,1,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (102,1,1,2,1,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (103,1,1,3,1,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (104,1,1,4,1,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (105,1,1,5,1,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (106,1,1,6,1,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (107,1,1,7,1,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (108,1,1,8,1,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (109,1,1,9,1,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (110,1,1,10,1,2)");


				// Piso 1 - Pasillo 2

				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (131,1,2,131,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (132,1,2,132,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (133,1,2,133,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (134,1,2,134,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (135,1,2,135,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (136,1,2,136,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (137,1,2,137,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (138,1,2,138,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (139,1,2,139,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (140,1,2,140,0,2)");

				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (111,1,2,1,1,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (112,1,2,2,1,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (113,1,2,3,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (114,1,2,4,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (115,1,2,5,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (116,1,2,6,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (117,1,2,7,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (118,1,2,8,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (119,1,2,9,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (120,1,2,10,0,2)");


				// Piso 2 - Pasillo 1
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (141,2,1,141,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (142,2,1,142,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (143,2,1,143,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (144,2,1,144,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (145,2,1,145,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (146,2,1,146,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (147,2,1,147,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (148,2,1,148,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (149,2,1,149,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (150,2,1,150,0,2)");

				// Piso 2 - Pasillo 2
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (131,2,2,151,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (132,2,2,152,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (133,2,2,153,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (134,2,2,154,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (135,2,2,155,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (136,2,2,156,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (137,2,2,157,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (138,2,2,158,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (139,2,2,159,0,2)");
				stmnt.execute("INSERT OR IGNORE INTO habitacion VALUES (140,2,2,160,0,2)");
				
				stmnt.executeUpdate(sqlEntrenador);
				stmnt.execute("INSERT OR IGNORE INTO entrenador VALUES (1, 1, 1, 101)");
				stmnt.execute("INSERT OR IGNORE INTO entrenador VALUES (2, 2, 2, 102)");
				stmnt.execute("INSERT OR IGNORE INTO entrenador VALUES (3, 3, 3, 103)");
				stmnt.execute("INSERT OR IGNORE INTO entrenador VALUES (4, 4, 4, 104)");
				stmnt.execute("INSERT OR IGNORE INTO entrenador VALUES (5, 5, 5, 105)");
				stmnt.execute("INSERT OR IGNORE INTO entrenador VALUES (6, 6, 6, 106)");


				stmnt.executeUpdate(sqlLugarAct);
				stmnt.execute("INSERT OR IGNORE INTO lugar_act (id_lugar, nombre_lugar, descripcion) "
						+ "VALUES (4, 'Piscina', '50 metros olímpica')");
				stmnt.execute("INSERT OR IGNORE INTO lugar_act (id_lugar, nombre_lugar, descripcion) "
						+ "VALUES (1, 'Campo de Futbol', 'descrpicion2')");
				stmnt.execute("INSERT OR IGNORE INTO lugar_act (id_lugar, nombre_lugar, descripcion) "
						+ "VALUES (3, 'Cancha de gimnasia', 'descrpicion3')");
				stmnt.execute("INSERT OR IGNORE INTO lugar_act (id_lugar, nombre_lugar, descripcion) "
						+ "VALUES (5, 'Campo de rugby', 'descrpicion4')");
				stmnt.execute("INSERT OR IGNORE INTO lugar_act (id_lugar, nombre_lugar, descripcion) "
						+ "VALUES (6, 'Pista atletismo', 'descrpicion5')");
				stmnt.execute("INSERT OR IGNORE INTO lugar_act (id_lugar, nombre_lugar, descripcion) "
						+ "VALUES (2, 'Cancha de baloncesto', 'descrpicion6')");

				
				stmnt.executeUpdate(sqlActividad);

				
				//ACTIVIDADES DE FUTBOL HASTA 2026-01-31
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (1, 1, 1, 1, '2025-11-25 15:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (2, 1, 1, '2025-11-26 17:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (3, 1, 1, '2025-11-27 10:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (4, 1, 1, '2025-11-28 12:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (5, 1, 1, '2025-11-29 18:00:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (6, 1, 1, '2025-11-30 15:30:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (7, 1, 1, '2025-12-01 17:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (8, 1, 1, '2025-12-02 10:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (9, 1, 1, '2025-12-03 18:00:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (10, 1, 1, '2025-12-04 12:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (11, 1, 1, '2025-12-05 15:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (12, 1, 1, '2025-12-06 18:00:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (13, 1, 1, '2025-12-07 10:00:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (14, 1, 1, '2025-12-08 12:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (15, 1, 1, '2025-12-09 17:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (16, 1, 1, '2025-12-10 10:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (17, 1, 1, '2025-12-11 15:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (18, 1, 1, '2025-12-12 17:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (19, 1, 1, '2025-12-13 18:00:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (20, 1, 1, '2025-12-14 12:00:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (21, 1, 1, '2025-12-15 15:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (22, 1, 1, '2025-12-16 17:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (23, 1, 1, '2025-12-17 18:00:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (24, 1, 1, '2025-12-18 10:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (25, 1, 1, '2025-12-19 12:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (26, 1, 1, '2025-12-20 18:00:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (27, 1, 1, '2025-12-21 15:30:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (28, 1, 1, '2025-12-22 17:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (29, 1, 1, '2025-12-23 12:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (30, 1, 1, '2025-12-24 10:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (31, 1, 1, '2025-12-25 12:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (32, 1, 1, '2025-12-26 15:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (33, 1, 1, '2025-12-27 18:00:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (34, 1, 1, '2025-12-28 10:00:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (35, 1, 1, '2025-12-29 17:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (36, 1, 1, '2025-12-30 10:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (37, 1, 1, '2025-12-31 12:00:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (38, 1, 1, '2026-01-01 12:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (39, 1, 1, '2026-01-02 17:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (40, 1, 1, '2026-01-03 18:00:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (41, 1, 1, '2026-01-04 15:30:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (42, 1, 1, '2026-01-05 10:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (43, 1, 1, '2026-01-06 12:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (44, 1, 1, '2026-01-07 18:00:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (45, 1, 1, '2026-01-08 10:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (46, 1, 1, '2026-01-09 15:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (47, 1, 1, '2026-01-10 18:00:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (48, 1, 1, '2026-01-11 12:00:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (49, 1, 1, '2026-01-12 10:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (50, 1, 1, '2026-01-13 15:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (51, 1, 1, '2026-01-14 17:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (52, 1, 1, '2026-01-15 12:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (53, 1, 1, '2026-01-16 17:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (54, 1, 1, '2026-01-17 18:00:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (55, 1, 1, '2026-01-18 10:00:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (56, 1, 1, '2026-01-19 12:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (57, 1, 1, '2026-01-20 15:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (58, 1, 1, '2026-01-21 18:00:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (59, 1, 1, '2026-01-22 12:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (60, 1, 1, '2026-01-23 10:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (61, 1, 1, '2026-01-24 18:00:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (62, 1, 1, '2026-01-25 15:30:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (63, 1, 1, '2026-01-26 17:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (64, 1, 1, '2026-01-27 10:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (65, 1, 1, '2026-01-28 12:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (66, 1, 1, '2026-01-29 15:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (67, 1, 1, '2026-01-30 17:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (68, 1, 1, '2026-01-31 18:00:00', 'Partido')");
				
				//ACTIVIDADES DE BALONCESTO
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (69, 2, 2, '2025-11-26 10:00:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (70, 2, 2, '2025-11-27 12:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (71, 2, 2, '2025-11-28 15:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (72, 2, 2, '2025-11-29 17:00:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (73, 2, 2, '2025-11-30 18:00:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (74, 2, 2, '2025-12-01 10:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (75, 2, 2, '2025-12-02 12:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (76, 2, 2, '2025-12-03 15:30:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (77, 2, 2, '2025-12-04 17:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (78, 2, 2, '2025-12-05 18:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (79, 2, 2, '2025-12-06 10:00:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (80, 2, 2, '2025-12-07 12:00:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (81, 2, 2, '2025-12-08 15:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (82, 2, 2, '2025-12-09 17:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (83, 2, 2, '2025-12-10 18:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (84, 2, 2, '2025-12-11 10:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (85, 2, 2, '2025-12-12 12:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (86, 2, 2, '2025-12-13 15:30:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (87, 2, 2, '2025-12-14 17:00:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (88, 2, 2, '2025-12-15 18:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (89, 2, 2, '2025-12-16 10:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (90, 2, 2, '2025-12-17 12:00:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (91, 2, 2, '2025-12-18 15:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (92, 2, 2, '2025-12-19 17:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (93, 2, 2, '2025-12-20 18:00:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (94, 2, 2, '2025-12-21 10:00:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (95, 2, 2, '2025-12-22 12:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (96, 2, 2, '2025-12-23 15:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (97, 2, 2, '2025-12-24 17:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (98, 2, 2, '2025-12-25 18:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (99, 2, 2, '2025-12-26 10:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (100, 2, 2, '2025-12-27 12:00:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (101, 2, 2, '2025-12-28 15:30:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (102, 2, 2, '2025-12-29 17:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (103, 2, 2, '2025-12-30 18:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (104, 2, 2, '2025-12-31 10:00:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (105, 2, 2, '2026-01-01 12:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (106, 2, 2, '2026-01-02 15:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (107, 2, 2, '2026-01-03 17:00:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (108, 2, 2, '2026-01-04 18:00:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (109, 2, 2, '2026-01-05 10:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (110, 2, 2, '2026-01-06 12:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (111, 2, 2, '2026-01-07 15:30:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (112, 2, 2, '2026-01-08 17:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (113, 2, 2, '2026-01-09 18:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (114, 2, 2, '2026-01-10 10:00:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (115, 2, 2, '2026-01-11 12:00:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (116, 2, 2, '2026-01-12 15:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (117, 2, 2, '2026-01-13 17:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (118, 2, 2, '2026-01-14 18:00:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (119, 2, 2, '2026-01-15 10:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (120, 2, 2, '2026-01-16 12:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (121, 2, 2, '2026-01-17 15:30:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (122, 2, 2, '2026-01-18 17:00:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (123, 2, 2, '2026-01-19 18:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (124, 2, 2, '2026-01-20 10:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (125, 2, 2, '2026-01-21 12:00:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (126, 2, 2, '2026-01-22 15:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (127, 2, 2, '2026-01-23 17:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (128, 2, 2, '2026-01-24 18:00:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (129, 2, 2, '2026-01-25 10:00:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (130, 2, 2, '2026-01-26 12:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (131, 2, 2, '2026-01-27 15:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (132, 2, 2, '2026-01-28 17:00:00', 'Entrenamiento')");  
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (133, 2, 2, '2026-01-29 18:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (134, 2, 2, '2026-01-30 10:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (135, 2, 2, '2026-01-31 12:00:00', 'Partido')");
				
				//ACTIVIDADES DE GIMNASIA
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (136, 3, 3, '2025-11-26 10:00:00', 'Competición')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (137, 3, 3, '2025-11-27 12:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (138, 3, 3, '2025-11-28 15:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (139, 3, 3, '2025-11-29 17:00:00', 'Competición')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (140, 3, 3, '2025-11-30 18:00:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (141, 3, 3, '2025-12-01 10:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (142, 3, 3, '2025-12-02 12:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (143, 3, 3, '2025-12-03 15:30:00', 'Competición')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (144, 3, 3, '2025-12-04 17:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (145, 3, 3, '2025-12-05 18:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (146, 3, 3, '2025-12-06 10:00:00', 'Competición')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (147, 3, 3, '2025-12-07 12:00:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (148, 3, 3, '2025-12-08 15:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (149, 3, 3, '2025-12-09 17:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (150, 3, 3, '2025-12-10 18:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (151, 3, 3, '2025-12-11 10:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (152, 3, 3, '2025-12-12 12:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (153, 3, 3, '2025-12-13 15:30:00', 'Competición')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (154, 3, 3, '2025-12-14 17:00:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (155, 3, 3, '2025-12-15 18:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (156, 3, 3, '2025-12-16 10:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (157, 3, 3, '2025-12-17 12:00:00', 'Competición')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (158, 3, 3, '2025-12-18 15:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (159, 3, 3, '2025-12-19 17:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (160, 3, 3, '2025-12-20 18:00:00', 'Competición')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (161, 3, 3, '2025-12-21 10:00:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (162, 3, 3, '2025-12-22 12:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (163, 3, 3, '2025-12-23 15:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (164, 3, 3, '2025-12-24 17:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (165, 3, 3, '2025-12-25 18:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (166, 3, 3, '2025-12-26 10:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (167, 3, 3, '2025-12-27 12:00:00', 'Competición')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (168, 3, 3, '2025-12-28 15:30:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (169, 3, 3, '2025-12-29 17:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (170, 3, 3, '2025-12-30 18:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (171, 3, 3, '2025-12-31 10:00:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (172, 3, 3, '2026-01-01 12:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (173, 3, 3, '2026-01-02 15:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (174, 3, 3, '2026-01-03 17:00:00', 'Competición')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (175, 3, 3, '2026-01-04 18:00:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (176, 3, 3, '2026-01-05 10:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (177, 3, 3, '2026-01-06 12:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (178, 3, 3, '2026-01-07 15:30:00', 'Competición')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (179, 3, 3, '2026-01-08 17:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (180, 3, 3, '2026-01-09 18:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (181, 3, 3, '2026-01-10 10:00:00', 'Competición')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (182, 3, 3, '2026-01-11 12:00:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (183, 3, 3, '2026-01-12 15:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (184, 3, 3, '2026-01-13 17:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (185, 3, 3, '2026-01-14 18:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (186, 3, 3, '2026-01-15 10:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (187, 3, 3, '2026-01-16 12:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (188, 3, 3, '2026-01-17 15:30:00', 'Competición')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (189, 3, 3, '2026-01-18 17:00:00', 'Entrenamiento')");
				
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (190, 3, 3, '2026-01-19 18:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (191, 3, 3, '2026-01-20 10:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (192, 3, 3, '2026-01-21 12:00:00', 'Competición')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (193, 3, 3, '2026-01-22 15:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (194, 3, 3, '2026-01-23 17:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (195, 3, 3, '2026-01-24 18:00:00', 'Competición')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (196, 3, 3, '2026-01-25 10:00:00', 'Entrenamiento')");
				
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (197, 3, 3, '2026-01-26 12:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (198, 3, 3, '2026-01-27 15:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (199, 3, 3, '2026-01-28 17:00:00', 'Competición')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (200, 3, 3, '2026-01-29 18:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (201, 3, 3, '2026-01-30 10:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (202, 3, 3, '2026-01-31 12:00:00', 'Competición')");
				
				//ACTIVIDADES NATACION
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (203, 4, 4, '2025-11-26 12:00:00', 'Competición')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (204, 4, 4, '2025-11-27 15:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (205, 4, 4, '2025-11-28 17:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (206, 4, 4, '2025-11-29 18:00:00', 'Competición')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (207, 4, 4, '2025-11-30 10:00:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (208, 4, 4, '2025-12-01 12:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (209, 4, 4, '2025-12-02 15:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (210, 4, 4, '2025-12-03 17:00:00', 'Competición')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (211, 4, 4, '2025-12-04 18:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (212, 4, 4, '2025-12-05 10:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (213, 4, 4, '2025-12-06 12:00:00', 'Competición')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (214, 4, 4, '2025-12-07 15:30:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (215, 4, 4, '2025-12-08 17:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (216, 4, 4, '2025-12-09 18:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (217, 4, 4, '2025-12-10 10:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (218, 4, 4, '2025-12-11 12:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (219, 4, 4, '2025-12-12 15:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (220, 4, 4, '2025-12-13 17:00:00', 'Competición')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (221, 4, 4, '2025-12-14 18:00:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (222, 4, 4, '2025-12-15 10:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (223, 4, 4, '2025-12-16 12:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (224, 4, 4, '2025-12-17 15:30:00', 'Competición')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (225, 4, 4, '2025-12-18 17:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (226, 4, 4, '2025-12-19 18:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (227, 4, 4, '2025-12-20 10:00:00', 'Competición')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (228, 4, 4, '2025-12-21 12:00:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (229, 4, 4, '2025-12-22 15:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (230, 4, 4, '2025-12-23 17:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (231, 4, 4, '2025-12-24 18:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (232, 4, 4, '2025-12-25 10:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (233, 4, 4, '2025-12-26 12:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (234, 4, 4, '2025-12-27 15:30:00', 'Competición')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (235, 4, 4, '2025-12-28 17:00:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (236, 4, 4, '2025-12-29 18:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (237, 4, 4, '2025-12-30 10:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (238, 4, 4, '2025-12-31 12:00:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (239, 4, 4, '2026-01-01 15:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (240, 4, 4, '2026-01-02 17:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (241, 4, 4, '2026-01-03 18:00:00', 'Competición')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (242, 4, 4, '2026-01-04 10:00:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (243, 4, 4, '2026-01-05 12:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (244, 4, 4, '2026-01-06 15:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (245, 4, 4, '2026-01-07 17:00:00', 'Competición')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (246, 4, 4, '2026-01-08 18:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (247, 4, 4, '2026-01-09 10:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (248, 4, 4, '2026-01-10 12:00:00', 'Competición')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (249, 4, 4, '2026-01-11 15:30:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (250, 4, 4, '2026-01-12 17:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (251, 4, 4, '2026-01-13 18:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (252, 4, 4, '2026-01-14 10:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (253, 4, 4, '2026-01-15 12:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (254, 4, 4, '2026-01-16 15:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (255, 4, 4, '2026-01-17 17:00:00', 'Competición')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (256, 4, 4, '2026-01-18 18:00:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (257, 4, 4, '2026-01-19 10:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (258, 4, 4, '2026-01-20 12:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (259, 4, 4, '2026-01-21 15:30:00', 'Competición')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (260, 4, 4, '2026-01-22 17:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (261, 4, 4, '2026-01-23 18:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (262, 4, 4, '2026-01-24 10:00:00', 'Competición')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (263, 4, 4, '2026-01-25 12:00:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (264, 4, 4, '2026-01-26 15:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (265, 4, 4, '2026-01-27 17:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (266, 4, 4, '2026-01-28 18:00:00', 'Competición')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (267, 4, 4, '2026-01-29 10:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (268, 4, 4, '2026-01-30 12:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (269, 4, 4, '2026-01-31 15:30:00', 'Competición')");
				
				//ACTIVIDADES RUGBY
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (270, 5, 5, '2025-11-26 12:30:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (271, 5, 5, '2025-11-27 17:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (272, 5, 5, '2025-11-28 15:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (273, 5, 5, '2025-11-29 18:30:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (274, 5, 5, '2025-11-30 10:30:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (275, 5, 5, '2025-12-01 12:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (276, 5, 5, '2025-12-02 17:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (277, 5, 5, '2025-12-03 15:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (278, 5, 5, '2025-12-04 18:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (279, 5, 5, '2025-12-05 10:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (280, 5, 5, '2025-12-06 12:30:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (281, 5, 5, '2025-12-07 17:30:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (282, 5, 5, '2025-12-08 15:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (283, 5, 5, '2025-12-09 18:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (284, 5, 5, '2025-12-10 10:30:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (285, 5, 5, '2025-12-11 12:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (286, 5, 5, '2025-12-12 17:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (287, 5, 5, '2025-12-13 15:00:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (288, 5, 5, '2025-12-14 18:30:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (289, 5, 5, '2025-12-15 10:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (290, 5, 5, '2025-12-16 12:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (291, 5, 5, '2025-12-17 17:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (292, 5, 5, '2025-12-18 15:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (293, 5, 5, '2025-12-19 18:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (294, 5, 5, '2025-12-20 10:30:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (295, 5, 5, '2025-12-21 12:30:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (296, 5, 5, '2025-12-22 17:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (297, 5, 5, '2025-12-23 15:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (298, 5, 5, '2025-12-24 18:30:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (299, 5, 5, '2025-12-25 10:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (300, 5, 5, '2025-12-26 12:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (301, 5, 5, '2025-12-27 17:30:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (302, 5, 5, '2025-12-28 15:00:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (303, 5, 5, '2025-12-29 18:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (304, 5, 5, '2025-12-30 10:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (305, 5, 5, '2025-12-31 12:30:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (306, 5, 5, '2026-01-01 17:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (307, 5, 5, '2026-01-02 15:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (308, 5, 5, '2026-01-03 18:30:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (309, 5, 5, '2026-01-04 10:30:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (310, 5, 5, '2026-01-05 12:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (311, 5, 5, '2026-01-06 17:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (312, 5, 5, '2026-01-07 15:00:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (313, 5, 5, '2026-01-08 18:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (314, 5, 5, '2026-01-09 10:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (315, 5, 5, '2026-01-10 12:30:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (316, 5, 5, '2026-01-11 17:30:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (317, 5, 5, '2026-01-12 15:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (318, 5, 5, '2026-01-13 18:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (319, 5, 5, '2026-01-14 10:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (320, 5, 5, '2026-01-15 12:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (321, 5, 5, '2026-01-16 17:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (322, 5, 5, '2026-01-17 15:00:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (323, 5, 5, '2026-01-18 18:30:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (324, 5, 5, '2026-01-19 10:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (325, 5, 5, '2026-01-20 12:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (326, 5, 5, '2026-01-21 17:30:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (327, 5, 5, '2026-01-22 15:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (328, 5, 5, '2026-01-23 18:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (329, 5, 5, '2026-01-24 10:30:00', 'Partido')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (330, 5, 5, '2026-01-25 12:30:00', 'Entrenamiento')");

				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (331, 5, 5, '2026-01-26 17:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (332, 5, 5, '2026-01-27 15:00:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (333, 5, 5, '2026-01-28 18:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (334, 5, 5, '2026-01-29 10:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (335, 5, 5, '2026-01-30 12:30:00', 'Entrenamiento')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (336, 5, 5, '2026-01-31 17:30:00', 'Partido')");
				
				//ACTIVIDADES ATLETISMO
				
				
				
				//ACTIVIDADES FUTBOL
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (1,1,1,'Entrenamiento','2025-11-26 11:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (2,1,1,'Partido','2025-11-26 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (3,1,1,'Entrenamiento','2025-11-27 12:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (4,1,1,'Entrenamiento','2025-11-28 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (5,1,1,'Entrenamiento','2025-11-29 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (6,1,1,'Partido','2025-11-29 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (7,1,1,'Entrenamiento','2025-11-30 12:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (8,1,1,'Entrenamiento','2025-12-01 15:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (9,1,1,'Entrenamiento','2025-12-02 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (10,1,1,'Entrenamiento','2025-12-03 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (11,1,1,'Partido','2025-12-03 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (12,1,1,'Entrenamiento','2025-12-04 12:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (13,1,1,'Entrenamiento','2025-12-05 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (14,1,1,'Entrenamiento','2025-12-06 15:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (15,1,1,'Partido','2025-12-06 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (16,1,1,'Entrenamiento','2025-12-07 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (17,1,1,'Entrenamiento','2025-12-08 15:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (18,1,1,'Entrenamiento','2025-12-09 12:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (19,1,1,'Entrenamiento','2025-12-10 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (20,1,1,'Entrenamiento','2025-12-11 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (21,1,1,'Entrenamiento','2025-12-12 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (22,1,1,'Entrenamiento','2025-12-13 12:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (23,1,1,'Partido','2025-12-13 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (24,1,1,'Entrenamiento','2025-12-14 15:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (25,1,1,'Entrenamiento','2025-12-15 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (26,1,1,'Entrenamiento','2025-12-16 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (27,1,1,'Entrenamiento','2025-12-17 12:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (28,1,1,'Partido','2025-12-17 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (29,1,1,'Entrenamiento','2025-12-18 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (30,1,1,'Entrenamiento','2025-12-19 15:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (31,1,1,'Entrenamiento','2025-12-20 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (32,1,1,'Partido','2025-12-20 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (33,1,1,'Entrenamiento','2025-12-21 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (34,1,1,'Entrenamiento','2025-12-22 12:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (35,1,1,'Entrenamiento','2025-12-23 15:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (36,1,1,'Entrenamiento','2025-12-24 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (37,1,1,'Entrenamiento','2025-12-25 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (38,1,1,'Entrenamiento','2025-12-26 12:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (39,1,1,'Entrenamiento','2025-12-27 15:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (40,1,1,'Partido','2025-12-27 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (41,1,1,'Entrenamiento','2025-12-28 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (42,1,1,'Entrenamiento','2025-12-29 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (43,1,1,'Entrenamiento','2025-12-30 15:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (44,1,1,'Entrenamiento','2025-12-31 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (45,1,1,'Partido','2025-12-31 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (46,1,1,'Entrenamiento','2026-01-01 12:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (47,1,1,'Entrenamiento','2026-01-02 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (48,1,1,'Entrenamiento','2026-01-03 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (49,1,1,'Partido','2026-01-03 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (50,1,1,'Entrenamiento','2026-01-04 12:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (51,1,1,'Entrenamiento','2026-01-05 15:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (52,1,1,'Entrenamiento','2026-01-06 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (53,1,1,'Entrenamiento','2026-01-07 12:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (54,1,1,'Partido','2026-01-07 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (55,1,1,'Entrenamiento','2026-01-08 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (56,1,1,'Entrenamiento','2026-01-09 15:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (57,1,1,'Entrenamiento','2026-01-10 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (58,1,1,'Partido','2026-01-10 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (59,1,1,'Entrenamiento','2026-01-11 12:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (60,1,1,'Entrenamiento','2026-01-12 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (61,1,1,'Entrenamiento','2026-01-13 15:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (62,1,1,'Entrenamiento','2026-01-14 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (63,1,1,'Entrenamiento','2026-01-15 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (64,1,1,'Entrenamiento','2026-01-16 12:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (65,1,1,'Entrenamiento','2026-01-17 15:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (66,1,1,'Partido','2026-01-17 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (67,1,1,'Entrenamiento','2026-01-18 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (68,1,1,'Entrenamiento','2026-01-19 12:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (69,1,1,'Entrenamiento','2026-01-20 15:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (70,1,1,'Entrenamiento','2026-01-21 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (71,1,1,'Partido','2026-01-21 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (72,1,1,'Entrenamiento','2026-01-22 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (73,1,1,'Entrenamiento','2026-01-23 12:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (74,1,1,'Entrenamiento','2026-01-24 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (75,1,1,'Partido','2026-01-24 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (76,1,1,'Entrenamiento','2026-01-25 15:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (77,1,1,'Entrenamiento','2026-01-26 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (78,1,1,'Entrenamiento','2026-01-27 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (79,1,1,'Entrenamiento','2026-01-28 12:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (80,1,1,'Entrenamiento','2026-01-29 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (81,1,1,'Entrenamiento','2026-01-30 15:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (82,1,1,'Entrenamiento','2026-01-31 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (83,1,1,'Partido','2026-01-31 18:30')");

				stmnt.executeUpdate(sqlDeportista);
				
				//futbolistas id_usuario 7tik aurrea,id_habitaicion(1-120)
				//sortutakun, aldau gelan ocupado a 1
				stmnt.execute("INSERT OR IGNORE INTO deportista (id_deportista, id_usuario, id_deporte, id_habitacion) "
						+ "VALUES (1, 7, 1, 1)");
				//
				

				//ACTIVIDADES BALONCESTO
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (84,2,2,'Entrenamiento','2025-11-26 15:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (85,2,2,'Partido','2025-11-26 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (86,2,2,'Entrenamiento','2025-11-27 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (87,2,2,'Entrenamiento','2025-11-28 17:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (88,2,2,'Entrenamiento','2025-11-29 10:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (89,2,2,'Partido','2025-11-29 17:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (90,2,2,'Entrenamiento','2025-11-30 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (91,2,2,'Entrenamiento','2025-12-01 15:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (92,2,2,'Entrenamiento','2025-12-02 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (93,2,2,'Entrenamiento','2025-12-03 10:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (94,2,2,'Partido','2025-12-03 17:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (95,2,2,'Entrenamiento','2025-12-04 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (96,2,2,'Entrenamiento','2025-12-05 15:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (97,2,2,'Entrenamiento','2025-12-06 17:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (98,2,2,'Partido','2025-12-06 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (99,2,2,'Entrenamiento','2025-12-07 10:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (100,2,2,'Entrenamiento','2025-12-08 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (101,2,2,'Entrenamiento','2025-12-09 15:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (102,2,2,'Entrenamiento','2025-12-10 17:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (103,2,2,'Entrenamiento','2025-12-11 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (104,2,2,'Entrenamiento','2025-12-12 10:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (105,2,2,'Entrenamiento','2025-12-13 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (106,2,2,'Partido','2025-12-13 17:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (107,2,2,'Entrenamiento','2025-12-14 15:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (108,2,2,'Entrenamiento','2025-12-15 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (109,2,2,'Entrenamiento','2025-12-16 10:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (110,2,2,'Entrenamiento','2025-12-17 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (111,2,2,'Partido','2025-12-17 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (112,2,2,'Entrenamiento','2025-12-18 17:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (113,2,2,'Entrenamiento','2025-12-19 15:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (114,2,2,'Entrenamiento','2025-12-20 10:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (115,2,2,'Partido','2025-12-20 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (116,2,2,'Entrenamiento','2025-12-21 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (117,2,2,'Entrenamiento','2025-12-22 15:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (118,2,2,'Entrenamiento','2025-12-23 17:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (119,2,2,'Entrenamiento','2025-12-24 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (120,2,2,'Entrenamiento','2025-12-25 10:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (121,2,2,'Entrenamiento','2025-12-26 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (122,2,2,'Entrenamiento','2025-12-27 15:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (123,2,2,'Partido','2025-12-27 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (124,2,2,'Entrenamiento','2025-12-28 17:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (125,2,2,'Entrenamiento','2025-12-29 10:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (126,2,2,'Entrenamiento','2025-12-30 15:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (127,2,2,'Entrenamiento','2025-12-31 17:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (128,2,2,'Partido','2025-12-31 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (129,2,2,'Entrenamiento','2026-01-01 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (130,2,2,'Entrenamiento','2026-01-02 10:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (131,2,2,'Entrenamiento','2026-01-03 17:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (132,2,2,'Partido','2026-01-03 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (133,2,2,'Entrenamiento','2026-01-04 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (134,2,2,'Entrenamiento','2026-01-05 15:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (135,2,2,'Entrenamiento','2026-01-06 10:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (136,2,2,'Entrenamiento','2026-01-07 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (137,2,2,'Partido','2026-01-07 17:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (138,2,2,'Entrenamiento','2026-01-08 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (139,2,2,'Entrenamiento','2026-01-09 15:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (140,2,2,'Entrenamiento','2026-01-10 10:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (141,2,2,'Partido','2026-01-10 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (142,2,2,'Entrenamiento','2026-01-11 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (143,2,2,'Entrenamiento','2026-01-12 17:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (144,2,2,'Entrenamiento','2026-01-13 15:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (145,2,2,'Entrenamiento','2026-01-14 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (146,2,2,'Entrenamiento','2026-01-15 10:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (147,2,2,'Entrenamiento','2026-01-16 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (148,2,2,'Entrenamiento','2026-01-17 15:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (149,2,2,'Partido','2026-01-17 17:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (150,2,2,'Entrenamiento','2026-01-18 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (151,2,2,'Entrenamiento','2026-01-19 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (152,2,2,'Entrenamiento','2026-01-20 15:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (153,2,2,'Entrenamiento','2026-01-21 17:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (154,2,2,'Partido','2026-01-21 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (155,2,2,'Entrenamiento','2026-01-22 10:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (156,2,2,'Entrenamiento','2026-01-23 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (157,2,2,'Entrenamiento','2026-01-24 17:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (158,2,2,'Partido','2026-01-24 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (159,2,2,'Entrenamiento','2026-01-25 15:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (160,2,2,'Entrenamiento','2026-01-26 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (161,2,2,'Entrenamiento','2026-01-27 10:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (162,2,2,'Entrenamiento','2026-01-28 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (163,2,2,'Entrenamiento','2026-01-29 17:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (164,2,2,'Entrenamiento','2026-01-30 15:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (165,2,2,'Entrenamiento','2026-01-31 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (166,2,2,'Partido','2026-01-31 17:30')");
				
				//ACTIVIDADES GIMNASIA
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (167,3,3,'Entrenamiento','2025-11-26 15:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (168,3,3,'Competicion','2025-11-26 17:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (169,3,3,'Entrenamiento','2025-11-27 12:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (170,3,3,'Entrenamiento','2025-11-28 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (171,3,3,'Entrenamiento','2025-11-29 15:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (172,3,3,'Competicion','2025-11-29 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (173,3,3,'Entrenamiento','2025-11-30 12:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (174,3,3,'Entrenamiento','2025-12-01 17:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (175,3,3,'Entrenamiento','2025-12-02 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (176,3,3,'Entrenamiento','2025-12-03 12:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (177,3,3,'Competicion','2025-12-03 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (178,3,3,'Entrenamiento','2025-12-04 15:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (179,3,3,'Entrenamiento','2025-12-05 17:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (180,3,3,'Entrenamiento','2025-12-06 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (181,3,3,'Competicion','2025-12-06 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (182,3,3,'Entrenamiento','2025-12-07 12:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (183,3,3,'Entrenamiento','2025-12-08 15:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (184,3,3,'Entrenamiento','2025-12-09 17:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (185,3,3,'Entrenamiento','2025-12-10 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (186,3,3,'Entrenamiento','2025-12-11 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (187,3,3,'Entrenamiento','2025-12-12 12:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (188,3,3,'Competicion','2025-12-13 17:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (189,3,3,'Entrenamiento','2025-12-13 15:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (190,3,3,'Entrenamiento','2025-12-14 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (191,3,3,'Entrenamiento','2025-12-15 12:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (192,3,3,'Entrenamiento','2025-12-16 15:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (193,3,3,'Entrenamiento','2025-12-17 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (194,3,3,'Competicion','2025-12-17 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (195,3,3,'Entrenamiento','2025-12-18 17:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (196,3,3,'Entrenamiento','2025-12-19 12:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (197,3,3,'Entrenamiento','2025-12-20 15:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (198,3,3,'Competicion','2025-12-20 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (199,3,3,'Entrenamiento','2025-12-21 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (200,3,3,'Entrenamiento','2025-12-22 12:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (201,3,3,'Entrenamiento','2025-12-23 15:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (202,3,3,'Entrenamiento','2025-12-24 17:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (203,3,3,'Entrenamiento','2025-12-25 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (204,3,3,'Entrenamiento','2025-12-26 12:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (205,3,3,'Entrenamiento','2025-12-27 15:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (206,3,3,'Competicion','2025-12-27 17:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (207,3,3,'Entrenamiento','2025-12-28 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (208,3,3,'Entrenamiento','2025-12-29 12:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (209,3,3,'Entrenamiento','2025-12-30 15:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (210,3,3,'Entrenamiento','2025-12-31 17:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (211,3,3,'Competicion','2025-12-31 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (212,3,3,'Entrenamiento','2026-01-01 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (213,3,3,'Entrenamiento','2026-01-02 12:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (214,3,3,'Entrenamiento','2026-01-03 15:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (215,3,3,'Competicion','2026-01-03 17:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (216,3,3,'Entrenamiento','2026-01-04 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (217,3,3,'Entrenamiento','2026-01-05 12:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (218,3,3,'Entrenamiento','2026-01-06 15:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (219,3,3,'Entrenamiento','2026-01-07 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (220,3,3,'Competicion','2026-01-07 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (221,3,3,'Entrenamiento','2026-01-08 17:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (222,3,3,'Entrenamiento','2026-01-09 15:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (223,3,3,'Entrenamiento','2026-01-10 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (224,3,3,'Competicion','2026-01-10 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (225,3,3,'Entrenamiento','2026-01-11 12:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (226,3,3,'Entrenamiento','2026-01-12 17:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (227,3,3,'Entrenamiento','2026-01-13 15:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (228,3,3,'Entrenamiento','2026-01-14 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (229,3,3,'Entrenamiento','2026-01-15 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (230,3,3,'Entrenamiento','2026-01-16 12:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (231,3,3,'Entrenamiento','2026-01-17 15:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (232,3,3,'Competicion','2026-01-17 17:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (233,3,3,'Entrenamiento','2026-01-18 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (234,3,3,'Entrenamiento','2026-01-19 12:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (235,3,3,'Entrenamiento','2026-01-20 15:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (236,3,3,'Entrenamiento','2026-01-21 17:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (237,3,3,'Competicion','2026-01-21 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (238,3,3,'Entrenamiento','2026-01-22 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (239,3,3,'Entrenamiento','2026-01-23 12:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (240,3,3,'Entrenamiento','2026-01-24 15:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (241,3,3,'Competicion','2026-01-24 17:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (242,3,3,'Entrenamiento','2026-01-25 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (243,3,3,'Entrenamiento','2026-01-26 12:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (244,3,3,'Entrenamiento','2026-01-27 15:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (245,3,3,'Entrenamiento','2026-01-28 17:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (246,3,3,'Entrenamiento','2026-01-29 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (247,3,3,'Entrenamiento','2026-01-30 12:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (248,3,3,'Entrenamiento','2026-01-31 15:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (249,3,3,'Competicion','2026-01-31 20:00')");


				//ACTIVIDADES NATACION
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (250,4,4,'Entrenamiento','2025-11-26 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (251,4,4,'Competicion','2025-11-26 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (252,4,4,'Entrenamiento','2025-11-27 11:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (253,4,4,'Entrenamiento','2025-11-28 09:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (254,4,4,'Entrenamiento','2025-11-29 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (255,4,4,'Competicion','2025-11-29 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (256,4,4,'Entrenamiento','2025-11-30 11:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (257,4,4,'Entrenamiento','2025-12-01 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (258,4,4,'Entrenamiento','2025-12-02 09:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (259,4,4,'Entrenamiento','2025-12-03 11:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (260,4,4,'Competicion','2025-12-03 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (261,4,4,'Entrenamiento','2025-12-04 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (262,4,4,'Entrenamiento','2025-12-05 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (263,4,4,'Entrenamiento','2025-12-06 09:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (264,4,4,'Competicion','2025-12-06 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (265,4,4,'Entrenamiento','2025-12-07 11:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (266,4,4,'Entrenamiento','2025-12-08 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (267,4,4,'Entrenamiento','2025-12-09 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (268,4,4,'Entrenamiento','2025-12-10 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (269,4,4,'Entrenamiento','2025-12-11 09:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (270,4,4,'Entrenamiento','2025-12-12 11:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (271,4,4,'Competicion','2025-12-13 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (272,4,4,'Entrenamiento','2025-12-13 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (273,4,4,'Entrenamiento','2025-12-14 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (274,4,4,'Entrenamiento','2025-12-15 11:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (275,4,4,'Entrenamiento','2025-12-16 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (276,4,4,'Entrenamiento','2025-12-17 09:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (277,4,4,'Competicion','2025-12-17 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (278,4,4,'Entrenamiento','2025-12-18 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (279,4,4,'Entrenamiento','2025-12-19 11:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (280,4,4,'Entrenamiento','2025-12-20 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (281,4,4,'Competicion','2025-12-20 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (282,4,4,'Entrenamiento','2025-12-21 09:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (283,4,4,'Entrenamiento','2025-12-22 11:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (284,4,4,'Entrenamiento','2025-12-23 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (285,4,4,'Entrenamiento','2025-12-24 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (286,4,4,'Entrenamiento','2025-12-25 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (287,4,4,'Entrenamiento','2025-12-26 11:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (288,4,4,'Entrenamiento','2025-12-27 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (289,4,4,'Competicion','2025-12-27 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (290,4,4,'Entrenamiento','2025-12-28 09:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (291,4,4,'Entrenamiento','2025-12-29 11:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (292,4,4,'Entrenamiento','2025-12-30 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (293,4,4,'Entrenamiento','2025-12-31 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (294,4,4,'Competicion','2025-12-31 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (295,4,4,'Entrenamiento','2026-01-01 09:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (296,4,4,'Entrenamiento','2026-01-02 11:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (297,4,4,'Entrenamiento','2026-01-03 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (298,4,4,'Competicion','2026-01-03 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (299,4,4,'Entrenamiento','2026-01-04 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (300,4,4,'Entrenamiento','2026-01-05 11:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (301,4,4,'Entrenamiento','2026-01-06 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (302,4,4,'Entrenamiento','2026-01-07 09:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (303,4,4,'Competicion','2026-01-07 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (304,4,4,'Entrenamiento','2026-01-08 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (305,4,4,'Entrenamiento','2026-01-09 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (306,4,4,'Entrenamiento','2026-01-10 09:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (307,4,4,'Competicion','2026-01-10 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (308,4,4,'Entrenamiento','2026-01-11 11:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (309,4,4,'Entrenamiento','2026-01-12 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (310,4,4,'Entrenamiento','2026-01-13 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (311,4,4,'Entrenamiento','2026-01-14 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (312,4,4,'Entrenamiento','2026-01-15 09:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (313,4,4,'Entrenamiento','2026-01-16 11:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (314,4,4,'Entrenamiento','2026-01-17 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (315,4,4,'Competicion','2026-01-17 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (316,4,4,'Entrenamiento','2026-01-18 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (317,4,4,'Entrenamiento','2026-01-19 11:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (318,4,4,'Entrenamiento','2026-01-20 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (319,4,4,'Entrenamiento','2026-01-21 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (320,4,4,'Competicion','2026-01-21 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (321,4,4,'Entrenamiento','2026-01-22 09:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (322,4,4,'Entrenamiento','2026-01-23 11:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (323,4,4,'Entrenamiento','2026-01-24 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (324,4,4,'Competicion','2026-01-24 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (325,4,4,'Entrenamiento','2026-01-25 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (326,4,4,'Entrenamiento','2026-01-26 11:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (327,4,4,'Entrenamiento','2026-01-27 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (328,4,4,'Entrenamiento','2026-01-28 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (329,4,4,'Entrenamiento','2026-01-29 09:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (330,4,4,'Entrenamiento','2026-01-30 11:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (331,4,4,'Entrenamiento','2026-01-31 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (332,4,4,'Competicion','2026-01-31 20:30')");

				//ACTIVIDADES RUGBY
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (333,5,5,'Entrenamiento','2025-11-26 16:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (334,5,5,'Partido','2025-11-26 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (335,5,5,'Entrenamiento','2025-11-27 11:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (336,5,5,'Entrenamiento','2025-11-28 09:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (337,5,5,'Entrenamiento','2025-11-29 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (338,5,5,'Partido','2025-11-29 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (339,5,5,'Entrenamiento','2025-11-30 11:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (340,5,5,'Entrenamiento','2025-12-01 16:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (341,5,5,'Entrenamiento','2025-12-02 09:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (342,5,5,'Entrenamiento','2025-12-03 11:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (343,5,5,'Partido','2025-12-03 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (344,5,5,'Entrenamiento','2025-12-04 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (345,5,5,'Entrenamiento','2025-12-05 16:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (346,5,5,'Entrenamiento','2025-12-06 09:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (347,5,5,'Partido','2025-12-06 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (348,5,5,'Entrenamiento','2025-12-07 11:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (349,5,5,'Entrenamiento','2025-12-08 16:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (350,5,5,'Entrenamiento','2025-12-09 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (351,5,5,'Entrenamiento','2025-12-10 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (352,5,5,'Entrenamiento','2025-12-11 09:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (353,5,5,'Entrenamiento','2025-12-12 11:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (354,5,5,'Partido','2025-12-13 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (355,5,5,'Entrenamiento','2025-12-13 16:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (356,5,5,'Entrenamiento','2025-12-14 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (357,5,5,'Entrenamiento','2025-12-15 11:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (358,5,5,'Entrenamiento','2025-12-16 16:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (359,5,5,'Entrenamiento','2025-12-17 09:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (360,5,5,'Partido','2025-12-17 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (361,5,5,'Entrenamiento','2025-12-18 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (362,5,5,'Entrenamiento','2025-12-19 11:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (363,5,5,'Entrenamiento','2025-12-20 16:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (364,5,5,'Partido','2025-12-20 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (365,5,5,'Entrenamiento','2025-12-21 09:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (366,5,5,'Entrenamiento','2025-12-22 11:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (367,5,5,'Entrenamiento','2025-12-23 16:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (368,5,5,'Entrenamiento','2025-12-24 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (369,5,5,'Entrenamiento','2025-12-25 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (370,5,5,'Entrenamiento','2025-12-26 11:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (371,5,5,'Entrenamiento','2025-12-27 16:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (372,5,5,'Partido','2025-12-27 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (373,5,5,'Entrenamiento','2025-12-28 09:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (374,5,5,'Entrenamiento','2025-12-29 11:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (375,5,5,'Entrenamiento','2025-12-30 16:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (376,5,5,'Entrenamiento','2025-12-31 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (377,5,5,'Partido','2025-12-31 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (378,5,5,'Entrenamiento','2026-01-01 09:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (379,5,5,'Entrenamiento','2026-01-02 11:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (380,5,5,'Entrenamiento','2026-01-03 16:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (381,5,5,'Partido','2026-01-03 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (382,5,5,'Entrenamiento','2026-01-04 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (383,5,5,'Entrenamiento','2026-01-05 11:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (384,5,5,'Entrenamiento','2026-01-06 16:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (385,5,5,'Entrenamiento','2026-01-07 09:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (386,5,5,'Partido','2026-01-07 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (387,5,5,'Entrenamiento','2026-01-08 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (388,5,5,'Entrenamiento','2026-01-09 16:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (389,5,5,'Entrenamiento','2026-01-10 09:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (390,5,5,'Partido','2026-01-10 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (391,5,5,'Entrenamiento','2026-01-11 11:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (392,5,5,'Entrenamiento','2026-01-12 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (393,5,5,'Entrenamiento','2026-01-13 16:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (394,5,5,'Entrenamiento','2026-01-14 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (395,5,5,'Entrenamiento','2026-01-15 09:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (396,5,5,'Entrenamiento','2026-01-16 11:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (397,5,5,'Entrenamiento','2026-01-17 16:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (398,5,5,'Partido','2026-01-17 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (399,5,5,'Entrenamiento','2026-01-18 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (400,5,5,'Entrenamiento','2026-01-19 11:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (401,5,5,'Entrenamiento','2026-01-20 16:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (402,5,5,'Entrenamiento','2026-01-21 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (403,5,5,'Partido','2026-01-21 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (404,5,5,'Entrenamiento','2026-01-22 09:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (405,5,5,'Entrenamiento','2026-01-23 11:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (406,5,5,'Entrenamiento','2026-01-24 16:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (407,5,5,'Partido','2026-01-24 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (408,5,5,'Entrenamiento','2026-01-25 20:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (409,5,5,'Entrenamiento','2026-01-26 11:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (410,5,5,'Entrenamiento','2026-01-27 16:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (411,5,5,'Entrenamiento','2026-01-28 18:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (412,5,5,'Entrenamiento','2026-01-29 09:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (413,5,5,'Entrenamiento','2026-01-30 11:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (414,5,5,'Partido','2026-01-31 20:00')");

				//ACTIVIDADES ATLETISMO
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (415,6,6,'Entrenamiento','2025-11-26 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (416,6,6,'Competicion','2025-11-26 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (417,6,6,'Entrenamiento','2025-11-27 11:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (418,6,6,'Entrenamiento','2025-11-28 09:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (419,6,6,'Entrenamiento','2025-11-29 18:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (420,6,6,'Competicion','2025-11-29 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (421,6,6,'Entrenamiento','2025-11-30 11:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (422,6,6,'Entrenamiento','2025-12-01 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (423,6,6,'Entrenamiento','2025-12-02 09:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (424,6,6,'Entrenamiento','2025-12-03 11:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (425,6,6,'Competicion','2025-12-03 18:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (426,6,6,'Entrenamiento','2025-12-04 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (427,6,6,'Entrenamiento','2025-12-05 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (428,6,6,'Entrenamiento','2025-12-06 09:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (429,6,6,'Competicion','2025-12-06 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (430,6,6,'Entrenamiento','2025-12-07 11:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (431,6,6,'Entrenamiento','2025-12-08 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (432,6,6,'Entrenamiento','2025-12-09 18:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (433,6,6,'Entrenamiento','2025-12-10 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (434,6,6,'Entrenamiento','2025-12-11 09:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (435,6,6,'Entrenamiento','2025-12-12 11:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (436,6,6,'Competicion','2025-12-13 18:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (437,6,6,'Entrenamiento','2025-12-13 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (438,6,6,'Entrenamiento','2025-12-14 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (439,6,6,'Entrenamiento','2025-12-15 11:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (440,6,6,'Entrenamiento','2025-12-16 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (441,6,6,'Entrenamiento','2025-12-17 09:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (442,6,6,'Competicion','2025-12-17 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (443,6,6,'Entrenamiento','2025-12-18 18:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (444,6,6,'Entrenamiento','2025-12-19 11:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (445,6,6,'Entrenamiento','2025-12-20 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (446,6,6,'Competicion','2025-12-20 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (447,6,6,'Entrenamiento','2025-12-21 09:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (448,6,6,'Entrenamiento','2025-12-22 11:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (449,6,6,'Entrenamiento','2025-12-23 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (450,6,6,'Entrenamiento','2025-12-24 18:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (451,6,6,'Entrenamiento','2025-12-25 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (452,6,6,'Entrenamiento','2025-12-26 11:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (453,6,6,'Entrenamiento','2025-12-27 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (454,6,6,'Competicion','2025-12-27 18:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (455,6,6,'Entrenamiento','2025-12-28 09:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (456,6,6,'Entrenamiento','2025-12-29 11:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (457,6,6,'Entrenamiento','2025-12-30 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (458,6,6,'Entrenamiento','2025-12-31 18:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (459,6,6,'Competicion','2025-12-31 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (460,6,6,'Entrenamiento','2026-01-01 09:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (461,6,6,'Entrenamiento','2026-01-02 11:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (462,6,6,'Entrenamiento','2026-01-03 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (463,6,6,'Competicion','2026-01-03 18:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (464,6,6,'Entrenamiento','2026-01-04 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (465,6,6,'Entrenamiento','2026-01-05 11:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (466,6,6,'Entrenamiento','2026-01-06 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (467,6,6,'Entrenamiento','2026-01-07 09:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (468,6,6,'Competicion','2026-01-07 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (469,6,6,'Entrenamiento','2026-01-08 18:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (470,6,6,'Entrenamiento','2026-01-09 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (471,6,6,'Entrenamiento','2026-01-10 09:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (472,6,6,'Competicion','2026-01-10 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (473,6,6,'Entrenamiento','2026-01-11 11:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (474,6,6,'Entrenamiento','2026-01-12 18:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (475,6,6,'Entrenamiento','2026-01-13 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (476,6,6,'Entrenamiento','2026-01-14 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (477,6,6,'Entrenamiento','2026-01-15 09:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (478,6,6,'Entrenamiento','2026-01-16 11:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (479,6,6,'Entrenamiento','2026-01-17 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (480,6,6,'Competicion','2026-01-17 18:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (481,6,6,'Entrenamiento','2026-01-18 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (482,6,6,'Entrenamiento','2026-01-19 11:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (483,6,6,'Entrenamiento','2026-01-20 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (484,6,6,'Competicion','2026-01-21 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (485,6,6,'Entrenamiento','2026-01-22 09:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (486,6,6,'Entrenamiento','2026-01-23 11:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (487,6,6,'Competicion','2026-01-24 18:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (488,6,6,'Entrenamiento','2026-01-25 20:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (489,6,6,'Entrenamiento','2026-01-26 11:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (490,6,6,'Entrenamiento','2026-01-27 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (491,6,6,'Entrenamiento','2026-01-28 18:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (492,6,6,'Entrenamiento','2026-01-29 09:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (493,6,6,'Entrenamiento','2026-01-30 11:30')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (494,6,6,'Entrenamiento','2026-01-31 16:00')");
				stmnt.execute("INSERT OR IGNORE INTO actividad VALUES (495,6,6,'Competicion','2026-01-31 20:30')");

				stmnt.executeUpdate(sqlDeportista);
				//FUTBOL
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (1, 7, 1, 1)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (2, 8, 1, 2)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (3, 9, 1, 3)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (4, 10, 1, 4)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (5, 11, 1, 5)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (6, 12, 1, 6)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (7, 13, 1, 7)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (8, 14, 1, 8)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (9, 15, 1, 9)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (10, 16, 1, 10)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (11, 17, 1, 11)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (12, 18, 1, 12)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (13, 19, 1, 13)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (14, 20, 1, 14)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (15, 21, 1, 15)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (16, 22, 1, 16)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (17, 23, 1, 17)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (18, 24, 1, 18)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (19, 25, 1, 19)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (20, 26, 1, 20)");
				
				//BALONCESTO
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (21, 27, 2, 21)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (22, 28, 2, 22)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (23, 29, 2, 23)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (24, 30, 2, 24)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (25, 31, 2, 25)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (26, 32, 2, 26)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (27, 33, 2, 27)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (28, 34, 2, 28)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (29, 35, 2, 29)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (30, 36, 2, 30)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (31, 37, 2, 31)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (32, 38, 2, 32)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (33, 39, 2, 33)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (34, 40, 2, 34)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (35, 41, 2, 35)");
				
				//GIMNASIA
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (36, 42, 3, 36)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (37, 43, 3, 37)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (38, 44, 3, 38)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (39, 45, 3, 39)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (40, 46, 3, 40)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (41, 47, 3, 41)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (42, 48, 3, 42)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (43, 49, 3, 43)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (44, 50, 3, 44)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (45, 51, 3, 45)");
				
				//NATACION
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (46, 52, 4, 46)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (47, 53, 4, 47)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (48, 54, 4, 48)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (49, 55, 4, 49)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (50, 56, 4, 50)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (51, 57, 4, 51)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (52, 58, 4, 52)");

				//RUGBY
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (53, 59, 5, 53)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (54, 60, 5, 54)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (55, 61, 5, 55)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (56, 62, 5, 56)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (57, 63, 5, 57)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (58, 64, 5, 58)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (59, 65, 5, 59)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (60, 66, 5, 60)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (61, 67, 5, 61)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (62, 68, 5, 62)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (63, 69, 5, 63)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (64, 70, 5, 64)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (65, 71, 5, 65)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (66, 72, 5, 66)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (67, 73, 5, 67)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (68, 74, 5, 68)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (69, 75, 5, 69)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (70, 76, 5, 70)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (71, 77, 5, 71)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (72, 78, 5, 72)");
				
				//ATLETISMO
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (73, 79, 6, 73)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (74, 80, 6, 74)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (75, 81, 6, 75)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (76, 82, 6, 76)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (77, 83, 6, 77)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (8, 84, 6, 78)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (79, 85, 6, 79)");
				stmnt.execute("INSERT OR IGNORE INTO deportista VALUES (80, 86, 6, 80)");
				
				
				stmnt.executeUpdate(sqlFisioterapeuta);
				stmnt.execute("INSERT OR IGNORE INTO fisioterapeuta VALUES (1, 87, 107)");
				stmnt.execute("INSERT OR IGNORE INTO fisioterapeuta VALUES (2, 88, 108)");
				stmnt.execute("INSERT OR IGNORE INTO fisioterapeuta VALUES (3, 89, 109)");
				stmnt.execute("INSERT OR IGNORE INTO fisioterapeuta VALUES (4, 90, 110)");
				stmnt.execute("INSERT OR IGNORE INTO fisioterapeuta VALUES (5, 91, 111)");
				stmnt.execute("INSERT OR IGNORE INTO fisioterapeuta VALUES (6, 92, 112)");
				
				stmnt.executeUpdate(sqlAdministrador);
				stmnt.execute("INSERT OR IGNORE INTO administrador VALUES (1, 93)");
				
				stmnt.executeUpdate(sqlCita);
				//FISIO FUTBOLISTAS
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (1,1,1,'2025-11-26 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (2,1,2,'2025-11-26 11:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (3,1,3,'2025-11-26 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (4,1,4,'2025-11-27 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (5,1,5,'2025-11-27 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (6,1,6,'2025-11-27 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (7,1,7,'2025-11-28 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (8,1,8,'2025-11-28 13:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (9,1,9,'2025-11-28 18:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (10,1,10,'2025-11-29 11:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (11,1,11,'2025-11-29 13:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (12,1,12,'2025-11-29 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (13,1,13,'2025-11-30 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (14,1,14,'2025-11-30 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (15,1,15,'2025-11-30 18:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (16,1,16,'2025-12-01 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (17,1,17,'2025-12-01 11:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (18,1,18,'2025-12-01 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (19,1,19,'2025-12-02 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (20,1,20,'2025-12-02 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (21,1,1,'2025-12-02 18:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (22,1,2,'2025-12-03 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (23,1,3,'2025-12-03 11:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (24,1,4,'2025-12-03 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (25,1,5,'2025-12-04 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (26,1,6,'2025-12-04 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (27,1,7,'2025-12-04 18:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (28,1,8,'2025-12-05 11:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (29,1,9,'2025-12-05 13:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (30,1,10,'2025-12-05 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (31,1,11,'2025-12-06 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (32,1,12,'2025-12-06 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (33,1,13,'2025-12-06 18:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (34,1,14,'2025-12-07 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (35,1,15,'2025-12-07 11:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (36,1,16,'2025-12-07 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (37,1,17,'2025-12-08 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (38,1,18,'2025-12-08 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (39,1,19,'2025-12-08 18:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (40,1,20,'2025-12-09 11:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (41,1,1,'2025-12-09 13:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (42,1,2,'2025-12-09 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (43,1,3,'2025-12-10 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (44,1,4,'2025-12-10 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (45,1,5,'2025-12-10 18:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (46,1,6,'2025-12-11 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (47,1,7,'2025-12-11 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (48,1,8,'2025-12-11 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (49,1,9,'2025-12-12 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (50,1,10,'2025-12-12 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (51,1,11,'2025-12-12 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (52,1,12,'2025-12-13 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (53,1,13,'2025-12-13 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (54,1,14,'2025-12-13 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (55,1,15,'2025-12-14 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (56,1,16,'2025-12-14 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (57,1,17,'2025-12-14 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (58,1,18,'2025-12-15 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (59,1,19,'2025-12-15 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (60,1,20,'2025-12-15 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (61,1,1,'2025-12-16 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (62,1,2,'2025-12-16 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (63,1,3,'2025-12-16 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (64,1,4,'2025-12-17 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (65,1,5,'2025-12-17 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (66,1,6,'2025-12-17 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (67,1,7,'2025-12-18 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (68,1,8,'2025-12-18 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (69,1,9,'2025-12-18 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (70,1,10,'2025-12-19 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (71,1,11,'2025-12-19 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (72,1,12,'2025-12-19 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (73,1,13,'2025-12-20 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (74,1,14,'2025-12-20 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (75,1,15,'2025-12-20 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (76,1,16,'2025-12-21 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (77,1,17,'2025-12-21 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (78,1,18,'2025-12-21 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (79,1,19,'2025-12-22 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (80,1,20,'2025-12-22 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (81,1,1,'2025-12-22 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (82,1,2,'2025-12-23 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (83,1,3,'2025-12-23 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (84,1,4,'2025-12-23 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (85,1,5,'2025-12-24 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (86,1,6,'2025-12-24 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (87,1,7,'2025-12-24 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (88,1,8,'2025-12-25 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (89,1,9,'2025-12-25 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (90,1,10,'2025-12-25 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (91,1,11,'2025-12-26 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (92,1,12,'2025-12-26 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (93,1,13,'2025-12-26 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (94,1,14,'2025-12-27 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (95,1,15,'2025-12-27 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (96,1,16,'2025-12-27 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (97,1,17,'2025-12-28 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (98,1,18,'2025-12-28 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (99,1,19,'2025-12-28 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (100,1,20,'2025-12-29 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (101,1,1,'2025-12-29 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (102,1,2,'2025-12-29 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (103,1,3,'2025-12-30 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (104,1,4,'2025-12-30 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (105,1,5,'2025-12-30 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (106,1,6,'2025-12-31 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (107,1,7,'2025-12-31 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (108,1,8,'2025-12-31 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (109,1,9,'2026-01-01 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (110,1,10,'2026-01-01 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (111,1,11,'2026-01-01 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (112,1,12,'2026-01-02 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (113,1,13,'2026-01-02 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (114,1,14,'2026-01-02 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (115,1,15,'2026-01-03 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (116,1,16,'2026-01-03 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (117,1,17,'2026-01-03 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (118,1,18,'2026-01-04 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (119,1,19,'2026-01-04 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (120,1,20,'2026-01-04 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (121,1,1,'2026-01-05 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (122,1,2,'2026-01-05 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (123,1,3,'2026-01-05 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (124,1,4,'2026-01-06 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (125,1,5,'2026-01-06 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (126,1,6,'2026-01-06 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (127,1,7,'2026-01-07 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (128,1,8,'2026-01-07 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (129,1,9,'2026-01-07 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (130,1,10,'2026-01-08 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (131,1,11,'2026-01-08 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (132,1,12,'2026-01-08 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (133,1,13,'2026-01-09 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (134,1,14,'2026-01-09 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (135,1,15,'2026-01-09 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (136,1,16,'2026-01-10 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (137,1,17,'2026-01-10 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (138,1,18,'2026-01-10 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (139,1,19,'2026-01-11 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (140,1,20,'2026-01-11 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (141,1,1,'2026-01-11 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (142,1,2,'2026-01-12 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (143,1,3,'2026-01-12 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (144,1,4,'2026-01-12 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (145,1,5,'2026-01-13 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (146,1,6,'2026-01-13 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (147,1,7,'2026-01-13 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (148,1,8,'2026-01-14 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (149,1,9,'2026-01-14 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (150,1,10,'2026-01-14 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (151,1,11,'2026-01-15 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (152,1,12,'2026-01-15 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (153,1,13,'2026-01-15 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (154,1,14,'2026-01-16 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (155,1,15,'2026-01-16 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (156,1,16,'2026-01-16 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (157,1,17,'2026-01-17 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (158,1,18,'2026-01-17 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (159,1,19,'2026-01-17 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (160,1,20,'2026-01-18 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (161,1,1,'2026-01-18 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (162,1,2,'2026-01-18 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (163,1,3,'2026-01-19 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (164,1,4,'2026-01-19 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (165,1,5,'2026-01-19 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (166,1,6,'2026-01-20 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (167,1,7,'2026-01-20 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (168,1,8,'2026-01-20 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (169,1,9,'2026-01-21 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (170,1,10,'2026-01-21 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (171,1,11,'2026-01-21 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (172,1,12,'2026-01-22 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (173,1,13,'2026-01-22 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (174,1,14,'2026-01-22 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (175,1,15,'2026-01-23 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (176,1,16,'2026-01-23 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (177,1,17,'2026-01-23 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (178,1,18,'2026-01-24 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (179,1,19,'2026-01-24 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (180,1,20,'2026-01-24 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (181,1,1,'2026-01-25 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (182,1,2,'2026-01-25 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (183,1,3,'2026-01-25 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (184,1,4,'2026-01-26 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (185,1,5,'2026-01-26 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (186,1,6,'2026-01-26 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (187,1,7,'2026-01-27 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (188,1,8,'2026-01-27 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (189,1,9,'2026-01-27 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (190,1,10,'2026-01-28 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (191,1,11,'2026-01-28 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (192,1,12,'2026-01-28 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (193,1,13,'2026-01-29 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (194,1,14,'2026-01-29 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (195,1,15,'2026-01-29 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (196,1,16,'2026-01-30 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (197,1,17,'2026-01-30 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (198,1,18,'2026-01-30 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (199,1,19,'2026-01-31 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (200,1,20,'2026-01-31 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (201,1,1,'2026-01-31 17:00')");

				//FISIO BALONCESTO
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (202,2,21,'2025-11-26 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (203,2,22,'2025-11-26 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (204,2,23,'2025-11-26 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (205,2,24,'2025-11-27 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (206,2,25,'2025-11-27 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (207,2,26,'2025-11-27 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (208,2,27,'2025-11-28 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (209,2,28,'2025-11-28 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (210,2,29,'2025-11-28 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (211,2,30,'2025-11-29 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (212,2,31,'2025-11-29 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (213,2,32,'2025-11-29 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (214,2,33,'2025-11-30 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (215,2,34,'2025-11-30 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (216,2,35,'2025-11-30 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (217,2,21,'2025-12-01 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (218,2,22,'2025-12-01 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (219,2,23,'2025-12-01 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (220,2,24,'2025-12-02 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (221,2,25,'2025-12-02 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (222,2,26,'2025-12-02 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (223,2,27,'2025-12-03 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (224,2,28,'2025-12-03 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (225,2,29,'2025-12-03 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (226,2,30,'2025-12-04 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (227,2,31,'2025-12-04 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (228,2,32,'2025-12-04 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (229,2,33,'2025-12-05 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (230,2,34,'2025-12-05 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (231,2,35,'2025-12-05 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (232,2,21,'2025-12-06 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (233,2,22,'2025-12-06 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (234,2,23,'2025-12-06 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (235,2,24,'2025-12-07 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (236,2,25,'2025-12-07 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (237,2,26,'2025-12-07 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (238,2,27,'2025-12-08 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (239,2,28,'2025-12-08 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (240,2,29,'2025-12-08 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (241,2,30,'2025-12-09 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (242,2,31,'2025-12-09 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (243,2,32,'2025-12-09 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (244,2,33,'2025-12-10 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (245,2,34,'2025-12-10 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (246,2,35,'2025-12-10 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (247,2,21,'2025-12-11 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (248,2,22,'2025-12-11 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (249,2,23,'2025-12-11 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (250,2,24,'2025-12-12 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (251,2,25,'2025-12-12 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (252,2,26,'2025-12-12 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (253,2,27,'2025-12-13 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (254,2,28,'2025-12-13 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (255,2,29,'2025-12-13 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (256,2,30,'2025-12-14 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (257,2,31,'2025-12-14 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (258,2,32,'2025-12-14 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (259,2,33,'2025-12-15 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (260,2,34,'2025-12-15 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (261,2,35,'2025-12-15 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (262,2,21,'2025-12-16 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (263,2,22,'2025-12-16 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (264,2,23,'2025-12-16 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (265,2,24,'2025-12-17 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (266,2,25,'2025-12-17 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (267,2,26,'2025-12-17 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (268,2,27,'2025-12-18 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (269,2,28,'2025-12-18 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (270,2,29,'2025-12-18 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (271,2,30,'2025-12-19 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (272,2,31,'2025-12-19 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (273,2,32,'2025-12-19 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (274,2,33,'2025-12-20 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (275,2,34,'2025-12-20 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (276,2,35,'2025-12-20 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (277,2,21,'2025-12-21 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (278,2,22,'2025-12-21 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (279,2,23,'2025-12-21 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (280,2,24,'2025-12-22 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (281,2,25,'2025-12-22 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (282,2,26,'2025-12-22 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (283,2,27,'2025-12-23 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (284,2,28,'2025-12-23 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (285,2,29,'2025-12-23 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (286,2,30,'2025-12-24 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (287,2,31,'2025-12-24 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (288,2,32,'2025-12-24 17:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (289,2,33,'2025-12-25 10:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (290,2,34,'2025-12-25 12:00')");
				stmnt.execute("INSERT OR IGNORE INTO cita VALUES (291,2,35,'2025-12-25 17:00')");
				//faltan todavia


				
				stmnt.executeUpdate(sqlHistoria);
				
		
				//para saber que hemos añadido usuarios
				String prueba="SELECT * FROM usuario";
				ResultSet rs=stmnt.executeQuery(prueba);
				while(rs.next()) {
				    System.out.println("ID: " + rs.getInt("id_usuario") +
				                       ", Nombre: " + rs.getString("nombre") +
				                       ", Apellido: " + rs.getString("apellidos") +
				                       ", Usuario: " + rs.getString("usuario") +
				                       ", Contraseña: " + rs.getString("contrasena"));
				}
				stmnt.close();
				conn.close();

			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		
		}

		public static void main(String[] args) {
			crearTablas();
		}
	}
