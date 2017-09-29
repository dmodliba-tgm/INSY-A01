<html>
    <head>
        <!-- Bootstrap CDN -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <!-- JQuery CDN -->
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

        <title>
            Flugdaten
        </title>
    </head>
    <body>
        <div class="container">
            <div class="row text-center">
                <div class="col-12">
                    <h1>
                        Flugnummer
                    </h1>
                    <hr>
                </div>
                <div class="col-12">
                    <form action="list.php" method="get">
                        <input type="text" placeholder="Flugnummer" name="fnr">

                        <button type="submit">
                            Suchen
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
