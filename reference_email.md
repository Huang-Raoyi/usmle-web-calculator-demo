### Coefficients for internal medince
![Alt text](image.png)
![Alt text](image-1.png)


### PHP codes for prob calculation
```
    $servername = "localhost"; 
    $username = "shvq4qke_tester";
    $password = "tester";
    $dbname = "shvq4qke_test";
          //capturing the fields from the form by the user
    $firstname = filter_input(INPUT_POST, 'fname');  
    $lastname = filter_input(INPUT_POST, 'lname');
    $email = filter_input(INPUT_POST, 'email');
    $step1exam = filter_input(INPUT_POST, 'step1Exam');
    $step1Score = filter_input(INPUT_POST, 'step1Score');
    $step1Failures = filter_input(INPUT_POST, 'step1Failures');
    $step2Exam = filter_input(INPUT_POST, 'step2Exam');
    $step2Score = filter_input(INPUT_POST, 'step2Score');
    $step2failures = filter_input(INPUT_POST, 'step2failures');
    $step2CSExam = filter_input(INPUT_POST, 'step2CSExam');
    $step2CSFailures = filter_input(INPUT_POST, 'step2CSFailures');
    $visaResidency = filter_input(INPUT_POST, 'visaResidency');
    $graduationYear = filter_input(INPUT_POST, 'graduationYear');
    $primarySpeciality = filter_input(INPUT_POST, 'primarySpeciality');
    $clinicalExperienceMonths = filter_input(INPUT_POST, 'clinicalExperienceMonths');
    $researchExperienceMonths = filter_input(INPUT_POST, 'researchExperienceMonths');
    $step3Exam = filter_input(INPUT_POST, 'step3Exam');
    $step3Score = filter_input(INPUT_POST, 'step3Score');
    $step3Failures = filter_input(INPUT_POST, 'step3Failures');
    $priorResidency = filter_input(INPUT_POST, 'priorResidency');
    $step1ScoreUpdated;
    $step2ScoreUpdated;
    $step3ScoreUpdated;
    $visaUpdated;
    $usceUpdated;
    $gap;
    $papers;
    $logodds;
    $probability;


    /* Appropriate Score is invoked when clicked on the corresponding option   and values gives in the statements below*/
    if ($step1exam == ("No")) {
        if ($graduationYear == 2018 && $visaResidency == ("Yes")) {
            if ($primarySpeciality == ("Internal Medicine")) {
                $step1Score = 0;
                $step1ScoreUpdated = 230;
            } else if ($primarySpeciality == ("Family Medicine")) {
                $step1Score = 0;
                $step1ScoreUpdated = 218;
            } else if ($primarySpeciality == ("Anesthesiology")) {
                $step1Score = 0;
                $step1ScoreUpdated = 241;
            }
        } else {
            if ($primarySpeciality == ("Internal Medicine")) {
                $step1Score = 0;
                $step1ScoreUpdated = 230;
            } else if ($primarySpeciality == ("Family Medicine")) {
                $step1Score = 0;
                $step1ScoreUpdated = 212;
            } else if ($primarySpeciality == ("Anesthesiology")) {
                $step1Score = 0;
                $step1ScoreUpdated = 239;
            }
        }
    } else {
        $step1ScoreUpdated = (int) $step1Score - ((int) $step1Failures * 15) - ((int) $step2CSFailures * 7);
        if ($step1ScoreUpdated < 0) {
            $step1ScoreUpdated = 0;
        }
    }
    if ($step2Exam == ("No")) {
        if ($graduationYear == 2018 && $visaResidency == ("Yes")) {
            if ($primarySpeciality == ("Internal Medicine")) {
                $step2Score = 0;
                $step2ScoreUpdated = 243;
            } else if ($primarySpeciality == ("Family Medicine")) {
                $step2Score = 0;
                $step2ScoreUpdated = 234;
            } else if ($primarySpeciality == ("Anesthesiology")) {
                $step2Score = 0;
                $step2ScoreUpdated = 241;
            }
        } else {
            if ($primarySpeciality == ("Internal Medicine")) {
                $step2Score = 0;
                $step2ScoreUpdated = 238;
            } else if ($primarySpeciality == ("Family Medicine")) {
                $step2Score = 0;
                $step2ScoreUpdated = 221;
            } else if ($primarySpeciality == ("Anesthesiology")) {
                $step2Score = 0;
                $step2ScoreUpdated = 239;
            }
        }
    } else {
        $step2ScoreUpdated = (int) $step2Score - ((int) $step2failures * 15) - ((int) $step2CSFailures * 8);
    }
    if ($step2failures > 0) {
        $step2ScoreUpdated = 238 - $step2ScoreUpdated - $step2Failures;
    }
    if ($visaResidency == ("Yes")) {
        $visaUpdated = 1;
    } else {
        $visaUpdated = 0;
    }
    $gap = 2017 - (int) $graduationYear;
    if ($priorResidency == ("Yes")) {
        $usceUpdated = (int) $clinicalExperienceMonths + 18;
    } else {
        $usceUpdated = (int) $clinicalExperienceMonths;
    }
    $modifiesResearchexperience = ((double) $researchExperienceMonths / 6);
    $papers = round($modifiesResearchexperience, 0, PHP_ROUND_HALF_UP);                     //Math.round((double) (researchExperienceMonths) / 6); 
    if ($step3Exam == ("Yes")) {
        $step3ScoreUpdated = 1;
    } else {
        $step3ScoreUpdated = 0;
    }


     // connection to the database.
    $conn = new mysqli($servername, $username, $password, $dbname);
    // fail gracefully if the connection goes wrong
    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }

    // Sql coefficient to get the cooefficients of the probability algorithm
    $sql = "SELECT * FROM Coefficients ORDER BY ID DESC LIMIT 1”;   >>>> when we have many specialities then????

    //Executing the sql query
    $coefficients = $conn->query($sql);



    if ($coefficients->num_rows > 0) {

        while ($row = $coefficients->fetch_assoc()) {
            
            //calculating logodds
            $logodds = -58 + $row["Step1updated"] * ($step1ScoreUpdated) + $row["CK"] * ($step2ScoreUpdated) + $row["VisaUpdated"] * ($visaUpdated) + $row["GAP"] * ($gap) + $row["UsceUpdated"] * ($usceUpdated) + $row["papers"] * ($papers) + $row["step3Updated"] * ($step3ScoreUpdated);} 

            //calculating the probability using logodds
            $probability = (exp($logodds) / (1 + exp($logodds))) * 100;
            
            //rounding off the probability to two decimal places
            $roundedProbability = round($probability, 2, PHP_ROUND_HALF_UP);
            
            //inserting into the sql table
            $sql_search="SELECT * FROM resident_data WHERE Email = '$email' ;";
            $result=$conn->query($sql_search);
            if($result->num_rows == 0)
            {
            $sql_insert = "INSERT INTO resident_data (`First_Name`, `Second_Name`, `Email`, `Probability`)
                     VALUES ( '".$firstname."', '".$lastname."', '".$email."', ".$roundedProbability.");";}
                     else
                     {
               $sql_insert="UPDATE resident_data SET Probability = '$roundedProbability' WHERE Email = '$email';”; 

>> when a user tries more than once with the same email????
>> store all the user’s scores ; as I mentioned that is more important than storing the probability as it can easily be recalculated if you have raw input                      
                     }
            
            
            if ($conn->query($sql_insert) === TRUE) {
                $subject = "Probability Calculator: USMLE Sarthi";
```