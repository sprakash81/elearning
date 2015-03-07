<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>eLearning - Add Account</title>
<style>
table, th, td {
	border: 1px solid grey;
	border-collapse: collapse;
	padding: 5px;
}

table tr:nth-child(odd) {
	background-color: #f1f1f1;
}

table tr:nth-child(even) {
	background-color: #ffffff;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
</head>
<body>
<body ng-app="elearingApp">
	<div ng-controller="registerAccount">
		<form name="inputForm" action="/accounts/register">
			<fieldset>
				<div ng-class="{error: inputForm.firstName.$invalid}">
					<label>Firstname *: </label>
					<input type="text" name="firstName" ng-required="true" ng-maxlength="60"/> 
					<span ng-show="inputForm.firstName.$error.required">Field required</span> 
					<span ng-show="inputForm.firstName.$error.maxlength">This field has a size of 60 characters maximum</span>
				</div>
				<div>
					<label>LastName *: </label>
					<input type="text" name="lastName" ng-required="true" ng-maxlength="60"/> 
					<span ng-show="inputForm.lastName.$error.required">Field required</span> 
					<span ng-show="inputForm.lastName.$error.maxlength">This field has a size of 60 characters maximum</span>
				</div>
				<div>
					<label>Email *: </label>
					<input type="email" name="emailId" ng-required="true" ng-maxlength="60"/> 
					<span ng-show="inputForm.emailId.$error.required">Field required</span> 
					<span ng-show="inputForm.emailId.$error.maxlength">This field has a size of 60 characters maximum</span>
				</div>
				<fieldset>
					<legend>Atleast one field is mandatory if filled.</legend>
					<div>
					<label>Street : </label>
						<input type="text" name="address.streetName" ng-required="true" ng-maxlength="255"/> 
					</div>
					<div>
						<label>Suburb : </label>
						<input type="text" name="address.suburb" ng-required="true" ng-maxlength="60"/> 
					</div>
					<div>
					<label>State : </label>
						<input type="text" name="address.state" ng-required="true" ng-maxlength="60"/> 
					</div>
					<div>
					<label>Country : </label>
						<input type="text" name="address.country" ng-required="true" ng-maxlength="60"/> 
					</div>
					<div>
					<label>Zip : </label>
						<input type="text" name="address.zipCode" ng-required="true" ng-maxlength="10"/> 
					</div>
				</fieldset>
				<button type="submit">Add User</button>
			</fieldset>
		</form>
	</div>
</body>
</html>