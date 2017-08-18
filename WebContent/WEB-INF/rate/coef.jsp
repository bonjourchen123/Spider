<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- ================================================================================================ --%>
<style>
	.maTable {
		width: 930px;
		border-style:solid;}
		
	.coefImg {
		position: relative; 
 		top: -20px; 
 		float:right;}
 		
 	.fontSize {
 		font-size: 0.4cm;}
 		
 	.myDiv {
		width: 150px;
		position: relative;
		top: 10px;
		left: 46%;}
</style>

<div class="title myDiv">保費相關係數</div>
<br>
<div class="title">
	強制險
</div>
<div class="fontSize">
	&nbsp;&nbsp;&nbsp;&nbsp;基本保費依被保人之保險費率等級
	<img class="coefImg" src="../images/coef.gif">
</div>
<br>
<div class="title">
	第三傷害責任險和第三責任財損險
</div>
<div class="fontSize">	
	&nbsp;&nbsp;&nbsp;&nbsp;1.自用車保費為基本保費&nbsp;x&nbsp;(從人因素係數+賠款紀錄係數)<br>
	&nbsp;&nbsp;&nbsp;&nbsp;2.營業用車保費為基本保費&nbsp;x&nbsp;(1+賠款紀錄係數)
</div>
<br>
<div class="title">從人因素係數</div>
<table class="maTable textCenter fontSize" border="1">
	<tr>
		<td>性別/年齡</td>
		<td>18歲~20歲</td>
		<td>21歲~25歲</td>
		<td>26歲~30歲</td>
		<td>31歲~60歲</td>
		<td>61歲~70歲</td>
		<td>71歲以上</td>
	<tr>
	<tr>
		<td>男</td>
		<td>1.89</td>
		<td>1.74</td>
		<td>1.15</td>
		<td>1.0</td>
		<td>1.07</td>
		<td>1.07</td>
	</tr>
	<tr>
		<td>女</td>
		<td>1.7</td>
		<td>1.57</td>
		<td>1.04</td>
		<td>0.9</td>
		<td>0.96</td>
		<td>0.96</td>
	</tr>
</table>
<br><br>
<div class="title">賠款紀錄係數</div>
<table class="maTable textCenter fontSize" border="1">
	<tr>
	    <td>等級</td>
	    <td>1</td>
	    <td>2</td>
	    <td>3</td>
	    <td>4</td>
	    <td>5</td>
	    <td>6</td>
	    <td>7</td>
	    <td>8</td>
	    <td>9</td>
	    <td>10</td>
	    <td>11</td>
		<td>12</td>
    	<td>13</td>
    	<td>14</td>
    	<td>15</td>
    	<td>16</td>
    	<td>17</td>
    	<td>18</td>
    	<td>19</td>
	</tr>
	<tr>
	    <td>係數</td>
	    <td>-0.3</td>
	    <td>-0.2</td>
	    <td>-0.1</td>
	    <td>0.0</td>
	    <td>0.1</td>
	    <td>0.2</td>
	    <td>0.3</td>
	    <td>0.4</td>
	    <td>0.5</td>
	    <td>0.6</td>
		<td>0.7</td>
	    <td>0.8</td>
	    <td>0.9</td>
	    <td>1.0</td>
	    <td>1.1</td>
	    <td>1.2</td>
	    <td>1.3</td>
	    <td>1.4</td>
	    <td>1.5</td>
	</tr>
</table>