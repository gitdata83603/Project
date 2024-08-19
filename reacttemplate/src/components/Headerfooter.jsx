// <!DOCTYPE html>
// <html lang="en">
// <head>
//     <meta charset="UTF-8">
//     <meta name="viewport" content="width=device-width, initial-scale=1.0">
//     <title>Document</title>
    
//     <style>
//         h1#h1d{color:aliceblue;text-align: center;}
//         div#div-3{background-color: darkcyan;height: 50px;align-content: center;}

//         h4#h1d{color:aliceblue;text-align: center;}
//         div#div-4{background-color: darkcyan;height: 30px;align-content: center;    margin-top: 500px;}
//     </style>

// </head>
// <body>
//     <header>
//         <div id="div-3"><h1 id="h1d">Online Railway Reservation</h1></div>
//     </header>
     
    
//    <footer>
//     <div id="div-4"><h4 id="h1d">copyright@c Railway Reservation system</h4></div>
//    </footer>

// </body>
// </html>



function Headerfooter() {
    

    return(
        <div>
         <header>
             <div id="div-3" style={{backgroundColor:'darkcyan' ,height: '80px',alignContent:'center'}}><h1 id="h1d" style={{color:'aliceblue',textAlign:'center'}}>Online Railway Reservation</h1></div>
         </header>

        {/* <footer>
        <div id="div-4"  style={{backgroundColor:'darkcyan' ,height: '40px',alignContent:'center',marginTop:'500px'}}><h4 id="h1d" style={{color:'aliceblue',textAlign:'center'}}>copyright@c Railway Reservation system</h4></div>
       </footer> */}
        </div>
    )
}

export default Headerfooter