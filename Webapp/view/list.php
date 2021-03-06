<html>
    <head>
        <meta charset="utf-8">
        <!-- Bootstrap CDN -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="../styles/styles.css">

        <!-- JQuery CDN -->
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

        <title>
            Fluginformationen
        </title>
    </head>
    <body>
        <div class="container">
            <div class="row text-center">
                <div class="col-12">
                    <h1>
                        Fluginformationen
                    </h1>
                    <hr>
                </div>
                <div class="col-12">
                    <?php

                        include 'config.php'; //config file für db als php file mit variablen deklaration und definition

                        //Ganzes DB Zeugs im try -> irgendwas läuft schieft -> entsprechender error wird ausgegeben (siehe catch)
                        try {

                            //stellt die verbindung zur db her
                            $conn = new PDO("$dbtype:host=$hostname;dbname=$dbname", $username, $password);
                            $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

                            //prepared statement um vor bösartigen usereingaben geschützt zu sein
                            $stmt = $conn -> prepare("SELECT * FROM flights WHERE airline LIKE :airlinecode AND flightnr=:flightnumber");
                            $stmt -> bindParam(":airlinecode", $airlinecode);
                            $stmt -> bindParam(":flightnumber", $flightnumber);

                            //teilt die usereingabe zb "aa000" auf in "aa" und "000" damit das format auf die datenbankstruktur passt
                            $input = $_GET['fnr'];
                            $airlinecode = substr($input, 0, 2);
                            $flightnumber = substr($input, 2, 5);

                            $stmt -> execute();

                            //ab hier alles im if
                            //grund: wenn die eingegebenen userdaten ein ergebnis produzieren kaönnen sie nicht fehlerhaft oder bösartig sein
                            //deswegen ab hier keine prepared statements mehr notwendig
                            if($stmt -> rowCount() > 0){
                                $flight = $stmt -> fetch();
                                $start_apc = $flight[3];
                                $dest_apc = $flight[5];
                            ?>

                            <div class="row text-left">
                                <!-- grafische aufbereitung der abfluginformationen -->
                                <div class="col-lg-6">
                                    <h2>
                                        Abflug
                                    </h2>
                                    <!-- Print of Start Country -->
                                    <b>Land: </b> <?php
                                    $result = $conn -> query("SELECT country FROM airports WHERE airportcode LIKE '$start_apc'");
                                    $row = $result -> fetch();
                                    $start_cc = $row[0];

                                    $result = $conn -> query("SELECT name FROM countries WHERE code LIKE '$start_cc'");
                                    $row = $result -> fetch();
                                    echo $row[0];
                                    ?><br>

                                    <!-- Print of Start City -->
                                    <b>Stadt: </b>
                                    <?php
                                    $result = $conn -> query("SELECT city FROM airports WHERE airportcode LIKE '$start_apc'");
                                    $row = $result -> fetch();
                                    echo $row[0];
                                    ?><br>

                                    <!-- Print of Start Airport -->
                                    <b>Flughafen: </b>
                                    <?php
                                    $result = $conn -> query("SELECT name FROM airports WHERE airportcode LIKE '$start_apc'");
                                    $row = $result -> fetch();
                                    echo $row[0] . " (" . $start_apc . ")";
                                    ?><br>

                                    <!-- Print of Start Date -->
                                    <b>Datum: </b>
                                    <?php
                                    $result = $conn -> query("SELECT EXTRACT(DAY FROM departure_time), EXTRACT(MONTH FROM departure_time), EXTRACT(YEAR FROM departure_time) FROM flights WHERE airline LIKE '$airlinecode' AND flightnr = $flightnumber");
                                    $row = $result -> fetch();
                                    echo str_pad($row[0], 2, "0", STR_PAD_LEFT) . "." . str_pad($row[1], 2, "0", STR_PAD_LEFT) . "." . $row[2];
                                    ?>
                                    <br>

                                    <!-- Print of Start Time -->
                                    <b>Uhrzeit: </b>
                                    <?php
                                    $result = $conn -> query("SELECT EXTRACT(HOUR FROM departure_time), EXTRACT(MINUTE FROM departure_time), EXTRACT(SECOND FROM departure_time) from flights WHERE airline LIKE '$airlinecode' AND flightnr = $flightnumber");
                                    $row = $result -> fetch();
                                    echo str_pad($row[0], 2, "0", STR_PAD_LEFT) . ":" . str_pad($row[1], 2, "0", STR_PAD_LEFT) . ":" . str_pad($row[2], 2, "0", STR_PAD_LEFT);
                                    ?>

                                    <hr>
                                </div>

                                <!-- grafische aufbereitung der ankunftsinformationen -->
                                <div class="col-lg-6">
                                    <h2>
                                        Ankunft
                                    </h2>
                                    <!-- Print of Destination Country -->
                                    <b>Land: </b> <?php
                                    $result = $conn -> query("SELECT country FROM airports WHERE airportcode LIKE '$dest_apc'");
                                    $row = $result -> fetch();
                                    $dest_cc = $row[0];

                                    $result = $conn -> query("SELECT name FROM countries WHERE code LIKE '$dest_cc'");
                                    $row = $result -> fetch();
                                    echo $row[0];
                                    ?><br>

                                    <!-- Print of Destination City -->
                                    <b>Stadt: </b>
                                    <?php
                                    $result = $conn -> query("SELECT city FROM airports WHERE airportcode LIKE '$dest_apc'");
                                    $row = $result -> fetch();
                                    echo $row[0];
                                    ?><br>

                                    <!-- Print of Destination Airport -->
                                    <b>Flughafen: </b>
                                    <?php
                                    $result = $conn -> query("SELECT name FROM airports WHERE airportcode LIKE '$dest_apc'");
                                    $row = $result -> fetch();
                                    echo $row[0] . " (" . $dest_apc . ")";
                                    ?><br>

                                    <!-- Print of Destination Date -->
                                    <b>Datum: </b>
                                    <?php
                                    $result = $conn -> query("SELECT EXTRACT(DAY FROM destination_time), EXTRACT(MONTH FROM destination_time), EXTRACT(YEAR FROM destination_time) FROM flights WHERE airline LIKE '$airlinecode' AND flightnr = $flightnumber");
                                    $row = $result -> fetch();
                                    echo str_pad($row[0], 2, "0", STR_PAD_LEFT) . "." . str_pad($row[1], 2, "0", STR_PAD_LEFT) . "." . $row[2];
                                    ?>
                                    <br>

                                    <!-- Print of Destination Time -->
                                    <b>Uhrzeit: </b>
                                    <?php
                                    $result = $conn -> query("SELECT EXTRACT(HOUR FROM destination_time), EXTRACT(MINUTE FROM destination_time), EXTRACT(SECOND FROM destination_time) from flights WHERE airline LIKE '$airlinecode' AND flightnr = $flightnumber");
                                    $row = $result -> fetch();
                                    echo str_pad($row[0], 2, "0", STR_PAD_LEFT) . ":" . str_pad($row[1], 2, "0", STR_PAD_LEFT) . ":" . str_pad($row[2], 2, "0", STR_PAD_LEFT);
                                    ?>

                                    <hr>
                                </div>
                            </div>
                            <!-- passagierliste -->
                            <div class="row">
                                <div class="col-lg-12">
                                    <h2 class="text-left">
                                        Passagierliste
                                    </h2>
                                    <table class="table table-striped">
                                        <tr>
                                            <th>
                                                Vorname
                                            </th>
                                            <th>
                                                Nachname
                                            </th>
                                            <th colspan="2">
                                                Sitznummer
                                            </th>
                                        </tr>
                                        <?php
                                        $result = $conn -> query("SELECT firstname, lastname, rownr, seatposition, id FROM passengers WHERE airline LIKE '$airlinecode' AND flightnr = $flightnumber ORDER BY 3 ASC, 4 ASC");
                                        while($row = $result -> fetch()){
                                            echo "<tr>";
                                            echo "<td>" . $row[0] . "</td>";
                                            echo "<td>" . $row[1] . "</td>";
                                            echo "<td>" . $row[2] . $row[3] . "</td>";
                                            echo "<form action='kick.php' method='post'>";
                                            echo "<td>";
                                            //verstecktes input feld mit der passenger_id als value damit der wert mittels post an das kick script überegben werden kann
                                            echo "<input type='text' style='visibility:hidden;' value=$row[4] name='passenger_id'>";
                                            echo "<button class='btn btn-danger' type='submit'>Kick</button></td>";
                                            echo "</form>";
                                            echo "</tr>";
                                        }
                                        ?>
                                    </div>
                                    </table>
                                    <?php
                                    //Print occupied seats
                                    echo $result -> rowCount();
                                    echo " / ";
                                    $result = $conn -> query("SELECT maxseats, manufacturer, type FROM planes WHERE id = $flight[6]");
                                    $row = $result -> fetch();
                                    echo $row[0];
                                    echo " Plätzen belegt<br>";

                                    //print plane type
                                    echo "Flugzeug: ";
                                    echo $row[1] . " " . $row[2];
                                    $result
                                    ?>
                                    <hr>
                                </div>
                            </div>
                            <?php
                            } else {
                                ?>
                                <div class="col-lg-12">
                                <?php
                                //Nutzer hat scheinbar fehlerhafte Daten eingegeben -> Diese nachricht wir angezeigt
                                echo "Ihre Eingabe ist eventuell fehlerhaft.";
                                ?>
                            </div>
                            <?php
                            }


                            $conn = null;
                        } catch(Exception $e){
                            //Programm hat ein Problem -> Diese Fehlermeldung wird angezeigt
                            echo "Error: " . $e->getMessage();
                        }
                        ?>
                    <a href="index.php">
                        &lt; Zurück
                    </a>
                </div>
            </div>
        </div>
    </body>
</html>
