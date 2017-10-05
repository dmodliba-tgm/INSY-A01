<?php

include 'config.php';

$passenger_id = $_POST['passenger_id'];
try {
    $conn = new PDO("$dbtype:host=$hostname;dbname=$dbname", $username, $password);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    $result = $conn -> query("SELECT airline, flightnr FROM passengers WHERE id=$passenger_id");
    $row = $result -> fetch();
    $fnr = $row[0] . $row[1];

    //löscht den passagier mit der entsprechenden passenger_id
    $conn -> query("DELETE FROM passengers WHERE id=$passenger_id");

    header("Location: list.php?fnr=$fnr");

} catch(Exception $e){
    echo "Error: " . $e -> getMessage();
}

?>
