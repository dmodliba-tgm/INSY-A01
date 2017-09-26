<html>
    <head>
        <!-- Bootstrap CDN -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

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
                </div>
                <div class="col-12">
                    <?php
                        $hostname = 'localhost';
                        $dbname = 'flightdata';
                        $username = 'dmodliba';
                        $password = '1234';
                        try {
                            $conn = new PDO("mysql:host=$hostname;dbname=$dbname", $username, $password);
                            $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

                            $stmt = $conn -> prepare("SELECT * FROM flights WHERE airline LIKE :airlinecode AND flightnr = :flightnumber");
                            $stmt -> bindParam(":airlinecode", $airlinecode);
                            $stmt -> bindParam(":flightnumber", $flightnumber);

                            $input = $_GET['fnr'];
                            $airlinecode = substr($input, 0, 2);
                            $flightnumber = substr($input, 2, 5);

                            $stmt -> execute();

                            if($stmt -> rowCount() > 0){
                                while($row = $stmt -> fetch()){
                                    echo "Airline: " . $row[0] . ", Flugnummer: " . $row[1] . ", Abflugzeit: " . $row[2] . ", Abflugort: " . $row[3] . ", Ankunftszeit: " . $row[4] . ", Ankunftsort: " . $row[5] . ", Flugzeug: " . $row[6];
                                }
                            } else {
                                echo "Ihre Eingabe ist eventuell fehlerhaft.";
                            }


                            $conn = null;
                        } catch(Exception $e){
                            echo "Error: " . $e->getMessage();
                        }
                       ?>
                </div>
            </div>
        </div>
    </body>
</html>
