
<!DOCTYPE html>
<html>

    <head>
        <title>500 Error</title>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
        <meta content="500 Error Page" name="description">
        <style>
            html {
                height: 100%;
            }
            html, body {
                margin:0;
                font-family: Arial, sans-serif;
                font-style: normal;
                font-variant: normal;
            }
            .pd-5vw{
                padding: 5vw;
            }

            .dpd-20{
                padding-bottom: 20px;
            }
            .text-center{
                text-align: center;
            }
            .text-red{
                color: #ff3366;
            }


            .text-size-20-vh{
                font-size: 20vh;
                line-height: normal;
            }

            .text-size-10-vh{
                font-size: 10vh;
            }

            .text-size-5-vh{
                font-size: 5vh;
            }











            .bg-error-500{
                height: 100%;
                position: relative;
                background: #eee;
                background: -moz-linear-gradient(top, rgba(255,219,219,1) 0%, rgba(255,255,255,1) 100%); /* FF3.6-15 */
                background: -webkit-linear-gradient(top, rgba(255,219,219,1) 0%,rgba(255,255,255,1) 100%); /* Chrome10-25,Safari5.1-6 */
                background: linear-gradient(to bottom, rgba(255,219,219,1) 0%,rgba(255,255,255,1) 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
                filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ffdbdb', endColorstr='#ffffff',GradientType=0 ); /* IE6-9 */
            }

            .error-img{
                position: absolute;
                text-align: right;
                right: 10vw;
                bottom: 10vh;
                width: 40%;
            }
            .error-img img{
                max-width: 100%;
            }

            .back-btn-500{
                text-decoration: none;
                font-size: 21px;
                padding: 10px 50px;
                font-weight: lighter;
                color: #fff;
                display: inline-block;
                border-radius: 500px;
                background: #ff3366;
                background: -moz-linear-gradient(left, rgba(255,86,86,1) 0%, rgba(244,184,53,1) 100%); /* FF3.6-15 */
                background: -webkit-linear-gradient(left, rgba(255,86,86,1) 0%,rgba(244,184,53,1) 100%); /* Chrome10-25,Safari5.1-6 */
                background: linear-gradient(to right, rgba(255,86,86,1) 0%,rgba(244,184,53,1) 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
                filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ff5656', endColorstr='#f4b835',GradientType=1 ); /* IE6-9 */
            }




            .bottom-copy{
                position: fixed;
                bottom: 0;
                left: 0;
                right: 0;
                min-height: 140px;
                text-align: center;
            }
            @media only screen and (max-width: 767px) {
                .sm-hide{
                    display: none;
                }
                .text-size-10-vh{
                    font-size: 6vh;
                }
            }
        </style>
    </head>
    <body class="bg-error-500">
        <section class="pd-5vw">
            <div class="text-red">
                <div class="text-size-20-vh">
                    <strong>
                        500
                    </strong>
                </div>
                <h1 class="text-size-5-vh dpd-20 qlariti-line-height-0">
                    Internal Server Error !
                </h1>
            </div>
            <div class="">
                <a href="<%=request.getContextPath()%>/index.jsp"  class="back-btn-500">&larr; Back To Home</a>
            </div>
        </section>
        <div class="error-img">
            <img src="
                 data:image/png;base64,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
                 " alt="">
        </div>

        <section class="bottom-copy">
            <div class="dpd-20">
                <a>
                    <svg height='50px' width='60px'  fill="#000000" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" xml:space="preserve" version="1.1" style="shape-rendering:geometricPrecision;text-rendering:geometricPrecision;image-rendering:optimizeQuality;" viewBox="0 0 333 333" x="0px" y="0px" fill-rule="evenodd" clip-rule="evenodd"><defs><style type="text/css">

                        .fil0 {
                            fill:#000000
                        }

                    </style></defs><g><g><path class="fil0" d="M113 156c3,-25 14,-47 31,-63 -8,-3 -17,-5 -27,-5 -29,0 -54,16 -68,39l-22 -11c18,-31 52,-53 90,-53 18,0 35,5 50,13 14,-8 31,-13 49,-13 39,0 72,22 90,53l-22 11c-14,-23 -39,-39 -68,-39 -9,0 -18,2 -27,5 17,16 28,38 31,63 -36,0 -71,0 -107,0zm75 -24c-5,-11 -12,-20 -21,-27 -9,7 -16,16 -22,27l43 0z"></path><path class="fil0" d="M220 180c-3,24 -15,45 -31,61 9,3 18,4 27,4 29,0 54,-15 68,-39l22 11c-18,32 -51,53 -90,53 -18,0 -35,-5 -49,-13 -15,8 -32,13 -50,13 -38,0 -72,-21 -90,-53l22 -11c14,24 39,39 68,39 10,0 19,-1 27,-4 -16,-16 -27,-37 -30,-61 35,0 70,0 106,0zm-73 25c5,9 12,17 20,23 8,-6 14,-14 19,-23l-39 0z"></path></g></g></svg>
                </a>
            </div>
            <div>&copy; SonNV.</div>
        </section>

    </body>
</html>	