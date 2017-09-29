<?php

$hostname = 'localhost';
$dbname = 'flightdata';
$username = 'dmodliba';
$password = '1234';

$passenger_id = $_POST['passenger_id'];
try {
    $conn = new PDO("mysql:host=$hostname;dbname=$dbname", $username, $password);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    $result = $conn -> query("SELECT airline, flightnr FROM passengers WHERE id=$passenger_id");
    $row = $result -> fetch();
    $fnr = $row[0] . $row[1];

    $conn -> query("DELETE FROM passengers WHERE id=$passenger_id");

    header("Location: list.php?fnr=$fnr");

} catch(Exception $e){
    echo "Error: " . $e -> getMessage();
}

?>
