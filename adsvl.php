<?php

date_default_timezone_set("Asia/Ho_Chi_Minh");
$BBlack="\033[1;30m" ; 
$BRed="\033[1;31m" ;
$BGreen="\033[1;32m" ;
$BYellow="\033[1;33m" ;
$BBlue="\033[1;34m" ;
$BPurple="\033[1;35m" ;
$BCyan="\033[1;36m" ;
$BWhite="\033[1;37m" ;
$Blue="\033[0;34m";
$lime="\033[1;32m";
$red="\033[1;31m";
$green = "\033[1;32m";
$cyan="\033[1;36m";
$yellow="\033[1;33m";
$turquoise="\033[1;34m";
$maugi="\033[1;35m";
$white= "\033[1;37m";
$logo="\033[1;33m─────────────────────────────────────────────────────────────────\n\033[1;31m[".$luc."●".$do."] ".$trang."=> \033[1;93mTools AdsVlog.com\n\033[1;31m[".$luc."●".$do."] ".$trang."=> \033[1;97mFacebook: \033[1;96mMạnh Giỏi\n\033[1;31m[".$luc."●".$do."] ".$trang."=> \033[1;97mTìm Kiến Nhanh: \033[1;93mm.me/lumanhgioi.vn\n\033[1;31m[".$luc."●".$do."] ".$trang."=> \033[1;97mZalo: \033[1;95m0333293290\n\033[1;31m[".$luc."●".$do."] ".$trang."=> \033[1;97mLink BoxChat: \033[1;32mhttps://zalo.me/g/nijmqx217\n\033[1;31m[".$luc."●".$do."] ".$trang."=> \033[1;97mYoutube: \033[1;34mShare Tool Vip\n\033[1;31m[".$luc."●".$do."] ".$trang."=> \033[1;97mMoMo: \033[1;31m0333293290\n\033[1;33m─────────────────────────────────────────────────────────────────\n";
@system('clear');
echo $green.'Input delay: ';
$delay = trim(fgets(STDIN));
$token = file_get_contents('https://api.phamvantoi.tk/backstage-adsvlog/authorization.txt');
$user ="Mozilla/5.0 (Linux; Android 10; K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Mobile Safari/537.36";
@system('clear');
$count_err = 0;
while(true){ 
// Get id video
sleep($delay);
$url = "https://rest.backstage.adsvlog.com/watch-earning/get-video/?locale=vi&appId=com.adsvlog.getsubscribers&firebaseMessagingToken=&platforms=android,mobile,mobileweb&rtl=false&landscape=false&portrait=false&width=360&height=630&last_visited_page=EarnWatching&app_build_date=2023-04-18T13:47:04Z&trackId=&referrerId=&geo=%7B%22city%22:%22Hanoi%22,%22organization%22:%22AS45899%20VNPT%20Corp%22,%22country%22:%22Vietnam%22,%22area_code%22:%220%22,%22organization_name%22:%22VNPT%20Corp%22,%22country_code%22:%22VN%22,%22country_code3%22:%22VNM%22,%22continent_code%22:%22AS%22,%22asn%22:45899,%22region%22:%22Hanoi%22,%22latitude%22:%2221.0292%22,%22longitude%22:%22105.8526%22,%22accuracy%22:5,%22ip%22:%2214.239.63.194%22,%22timezone%22:%22Asia/Bangkok%'clear'";
$mr = curl_init();
curl_setopt_array($mr, array(
CURLOPT_PORT => "443",
CURLOPT_URL => "$url",
CURLOPT_ENCODING => "",
CURLOPT_RETURNTRANSFER => true,
CURLOPT_SSL_VERIFYPEER => false,
CURLOPT_TIMEOUT => 30,
CURLOPT_CUSTOMREQUEST => "GET",
CURLOPT_HTTPHEADER => array(
"Host:rest.backstage.adsvlog.com",
"authorization:$token",
"user-agent:$user",
"referer:https://backstage.adsvlog.com/earn-watching",
"cookie:_fbp=fb.1.1616130037619.1987669943;_ga=GA1.1.2119931499.1616130054;_ga_3V5Q2KS7S0=GS1.1.1616130051.1.1.1616130500.0",
)));
$mr2 = curl_exec($mr); curl_close($mr);
$json = json_decode($mr2,true);
if(!isset($json["_id"])){
  $count_err++;
  if($count_err >= 2){
    sleep(30);
  }
  $token = file_get_contents('https://api.phamvantoi.tk/backstage-adsvlog/authorization.txt');
  echo $green.date("h").':'.date("i")." | Got new authorization\n";
  continue;
}
$id = $json['_id'];
// Get id video 2
$url = "https://rest.backstage.adsvlog.com/watch-earning/credit-video/?locale=vi&appId=com.adsvlog.getsubscribers&firebaseMessagingToken=&platforms=android,mobile,mobileweb&rtl=false&landscape=false&portrait=false&width=360&height=630&last_visited_page=EarnWatching&app_build_date=2023-04-18T13:47:04Z&trackId=&referrerId=&geo=%7B%22city%22:%22Hanoi%22,%22organization%22:%22AS45899%20VNPT%20Corp%22,%22country%22:%22Vietnam%22,%22area_code%22:%220%22,%22organization_name%22:%22VNPT%20Corp%22,%22country_code%22:%22VN%22,%22country_code3%22:%22VNM%22,%22continent_code%22:%22AS%22,%22asn%22:45899,%22region%22:%22Hanoi%22,%22latitude%22:%2221.0292%22,%22longitude%22:%22105.8526%22,%22accuracy%22:5,%22ip%22:%2214.239.63.194%22,%22timezone%22:%22Asia/Bangkok%22%7D";
 
$data = '{"adId":"'.$id.'"}';
 
$mr = curl_init();
curl_setopt_array($mr, array(
CURLOPT_PORT => "443",
CURLOPT_URL => "$url",
CURLOPT_ENCODING => "",
CURLOPT_RETURNTRANSFER => true,
CURLOPT_SSL_VERIFYPEER => false,
CURLOPT_TIMEOUT => 30,
CURLOPT_CUSTOMREQUEST => "POST",
CURLOPT_POSTFIELDS => $data,
CURLOPT_HTTPHEADER => array(
"Host:rest.backstage.adsvlog.com",
"authorization:$token",
"user-agent:$user",
"watching-metrics:eyJwbGF5UGF1c2VCb251cyI6eyJyZWNlaXZlZCI6ZmFsc2V9LCJyZXdpbmRCb251cyI6eyJyZWNlaXZlZCI6ZmFsc2V9LCJjaGFuZ2VRdWFsaXR5Qm9udXMiOnsicmVjZWl2ZWQiOmZhbHNlfSwid2F0Y2hUaWxsRW5kQm9udXMiOnsicmVjZWl2ZWQiOmZhbHNlfX0=",
"performance-metrics:eyJyZXF1aXJlZER1cmF0aW9uSW5TZWNvbmRzIjo4LCJ0aW1lU3RhbXBzIjp7ImxvYWRlZCI6MTY4NjQ0MDc5NzU5Nywic3RhcnRlZCI6MTY4NjQ0MDgwNzAyNCwiZmluaXNoZWQiOjE2ODY0NDA4MTUxMDh9fQ==",
"origin:https://backstage.adsvlog.com",
"referer:https://backstage.adsvlog.com/earn-watching",
"content-type:application/json",
"cookie:_fbp=fb.1.1616130037619.1987669943;_ga=GA1.1.2119931499.1616130054;_ga_3V5Q2KS7S0=GS1.1.1616130051.1.1.1616130500.0",
)));
$mr2 = curl_exec($mr); curl_close($mr);
echo $mr2."\n";
$count_err = 0;
//$json = json_decode($mr2,true);
// nhan tien
/*$url = "https://adsvlog.com/api/current-user/?locale=vi&platforms=android,phablet,mobile,mobileweb&rtl=false&landscape=false&portrait=false&width=412&height=669&last_visited_page=EarnWatching&geo=%7B%22organization_name%22:%22The%20Corporation%20for%20Financing%20%26%20Promoting%20Technology%22,%22region%22:%22Hanoi%22,%22accuracy%22:1000,%22asn%22:18403,%22organization%22:%22AS18403%20The%20Corporation%20for%20Financing%20%26%20Promoting%20Technology%22,%22timezone%22:%22Asia/Bangkok%22,%22longitude%22:%22105.8516%22,%22country_code3%22:%22VNM%22,%22area_code%22:%220%22,%22ip%22:%2242.114.151.119%22,%22city%22:%22Hanoi%22,%22country%22:%22Vietnam%22,%22continent_code%22:%22AS%22,%22country_code%22:%22VN%22,%22latitude%22:%2221.0313%22%7D";
 
$mr = curl_init();
curl_setopt_array($mr, array(
CURLOPT_PORT => "443",
CURLOPT_URL => "$url",
CURLOPT_ENCODING => "",
CURLOPT_RETURNTRANSFER => true,
CURLOPT_SSL_VERIFYPEER => false,
CURLOPT_TIMEOUT => 30,
CURLOPT_CUSTOMREQUEST => "GET",
CURLOPT_HTTPHEADER => array(
"Host:adsvlog.com",
"authorization:$token",
"user-agent:$user",
"referer:https://adsvlog.com/earn-watching",
"cookie:_fbp=fb.1.1616130037619.1987669943;_ga=GA1.1.2119931499.1616130054;_ga_3V5Q2KS7S0=GS1.1.1616130051.1.1.1616130500.0",
)));
$mr2 = curl_exec($mr); curl_close($mr);
$json = json_decode($mr2,true);
$nt = $json['balance'];
$time = $json['serverTime'];
$so++;
echo $BCyan."[$so] $BRed ●$BGreen [SUCCESS]$BRed ●$BGreen ID $BRed ●$BGreen $id $BRed ●$BGreen Tổng Xu $BRed ●$BGreen $BYellow $nt \n";
}
function chay($so){
  for($v=0;$v<= $so;$v++){
    echo "\033[1;36m"."▬";usleep(20000);echo "\033[1;37m"."▬";usleep(20000);echo "\033[1;36m"."▬";usleep(20000);echo "\033[1;37m"."▬";usleep(20000);
} echo "\n";*/
}