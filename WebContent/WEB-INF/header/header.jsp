<style>
	/*最上頭部分*/
	header {
		width: 960px;
		height: 130px;
		background-image: url(../images/header.jpg);
		padding-top: 20px;
		padding-bottom: 0px;
		margin: auto;
		margin-top: 10px;
		border-style: ridge;
		border-radius: 15px 15px 0px 0px;
		box-shadow: rgba(0, 0, 0, 0.5) 0px 0px 5px;}

	/*商標*/
	header img {
		height: 80px;
		padding-left: 10px;
		float: left;
		margin: 15px;
		margin-top: 0px;}

	header div {
		background: transparent;
		height: 50px;
		margin: 0 auto;
		margin-top: -15px;
		margin-bottom: 10px;
		padding-left: 20px;
		font: 1.2cm 'News Cycle', "Microsoft YaHei", Arial;
		letter-spacing: -1px;
		text-shadow: 0px -1px 0px;
		color: #228B22;}

	/*系統名稱顏色*/
	header .system{
		color: #4169E1;}

	/*標語*/
	header .slogan {
		font: 0.8cm 'News Cycle', "Microsoft YaHei", Arial;
		letter-spacing: -2px;
		color: black;}

	/*登入的帳號*/
	header .account{
		width: 150px;
		position: relative;
		top:-26px;
		left: 400px;
		text-align: left;
		font: 0.38cm 'News Cycle', "Microsoft YaHei", Arial;
		letter-spacing: 2px;
		color: black;}
		
	/*使用者帳號 姓名*/
	header .user{
		font: 0.35cm 'News Cycle', "Microsoft YaHei", Arial;}

	/*登出*/
	header .leave{
		width: 80px;
		position: relative;
		top: -2px;
 		left: 450px;
		font: 0.425cm 'News Cycle', "Microsoft YaHei", Arial;
		letter-spacing: 1px;
		color: #4169E1;
		clear: left;
		z-index: 2;}

	header .leave a{
		text-decoration: none;
		color: #4169E1;}
</style>

<header>
	<a href="../auth/index">
		<img alt="Spider車險投保系統" title="點此回首頁" src="../images/trademark.png">
	</a>
	<div>
		Spider <span class="system">車險投保系統</span>
	</div>
	<span class="slogan">資策會-保險公司</span>
	<div class="account">
		部門:<span class="user">${session.login.officeLocalName}</span><br>
		帳號:<span class="user">${session.login.account}</span><br>
		姓名:<span class="user">${session.login.name}</span>
	</div>
	<div class="leave"><a href="../auth/logout">登出</a></div>
</header>