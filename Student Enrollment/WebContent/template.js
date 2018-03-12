function validate(){
    let year = document.getElementById('year_id');
    let month = document.getElementById('month_id');
    let day = document.getElementById('day_id');
    if(month <=7 && month%2!=0 && month!=2){
        if(day<=31 && day>=1){
            return true;
        }else{
            alert("Invalid Details");
            return false;
        }
    }
    if(month <=7 && month%2==0 && month!=2){
        if(day<=30 && day>=1){
            return true;
        }else{
            alert("Invalid Details");
            return false;
        }
    }
    if(month > 7 && month%2==0){
        if(day<=31 && day >=1){
            return true;
        }else{
            alert("Invalid Details");
            return false;
        }
    }
    if(month > 7 && month%2!=0){
        if(day<=30 && day>=1){
            return true;
        }else{
            alert("Invalid Details");
            return false;
        }
    }
    if(month==2 &&  (year % 100 === 0) ? (year % 400 === 0) : (year % 4 === 0) ){
        if(day <=29 && day>=1){
            return true;
        }else{
            alert("Invalid Details");
            return false;
        }
    }
    if(month==2 && !(year % 100 === 0) ? (year % 400 === 0) : (year % 4 === 0)){
        if(day <=28 && day>=1){
            return true;
        }else{
            alert("Invalid Details");
            return false;
        }
    }
}