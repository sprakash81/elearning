<!DOCTYPE html>
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
	<div ng-show="showMessage() == true">{{$scope.message}}</div>
		<form name="inputForm">
			<fieldset>
				<div ng-class="{error: inputForm.firstName.$invalid}">
					<label>Firstname *: </label>
					<input type="text" name="firstName" ng-required="true" ng-maxlength="60" ng-model="account.firstName"/> 
					<span ng-show="inputForm.firstName.$error.required">Field required</span> 
					<span ng-show="inputForm.firstName.$error.maxlength">This field has a size of 60 characters maximum</span>
				</div>
				<div>
					<label>LastName *: </label>
					<input type="text" name="lastName" ng-required="true" ng-maxlength="60" ng-model="account.lastName"/> 
					<span ng-show="inputForm.lastName.$error.required">Field required</span> 
					<span ng-show="inputForm.lastName.$error.maxlength">This field has a size of 60 characters maximum</span>
				</div>
				<div>
					<label>Email *: </label>
					<input type="email" name="emailId" ng-required="true" ng-maxlength="60" ng-model="account.emailId"/> 
					<span ng-show="inputForm.emailId.$error.required">Field required</span> 
					<span ng-show="inputForm.emailId.$error.maxlength">This field has a size of 60 characters maximum</span>
				</div>
				<fieldset>
					<legend>Atleast one field is mandatory if filled.</legend>
					<div>
					<label>Street : </label>
						<input type="text" name="address.streetName" ng-maxlength="255" ng-model="account.address.streetName"/> 
					</div>
					<div>
						<label>Suburb : </label>
						<input type="text" name="address.suburb" ng-maxlength="60" ng-model="account.address.suburb"/> 
					</div>
					<div>
					<label>State : </label>
						<input type="text" name="address.state" ng-maxlength="60" ng-model="account.address.state"/> 
					</div>
					<div>
					<label>Country : </label>
						<input type="text" name="address.country" ng-maxlength="60" ng-model="account.address.country"/> 
					</div>
					<div>
					<label>Zip : </label>
						<input type="text" name="address.zipCode" ng-maxlength="10" ng-model="account.address.zipCode"/> 
					</div>
				</fieldset>
				<button type="button" ng-click="submit()">Add User</button>
			</fieldset>
		</form>
	</div>
	
	<script>
		var elearningApp = angular.module("elearingApp", []);

		elearningApp.controller("registerAccount", function($scope, $http) {
			$scope.message = '';
			$scope.account = new Object();
			$scope.showMessage = function(){
				console.log("the value of message is "+$scope.message);
				var returnVal = $scope.message ==  '' ? false : true;
				console.log("returning the value "+returnVal);
				return returnVal; 
			};
			$scope.submit = function() {
				console.log("in here");
				$http({
		            method :'POST',
		            url:'./register',
		            data: $scope.account,
		            headers: {
                        "Content-Type": "application/json",
                        "Accept": "text/plain, application/json"
                    }
		        }).success(function(response){
		        	$scope.account = new Object();
		        	$scope.message = "Save is successful.";
		        	
		        }).error(function(response){
		        	$scope.message = "Received error, kindly retry.";
				});
				
				/* $http.post("./add", $scope.newCourse).success(function(response) {
					$scope.message = response;
				}); */
			}
		});
	</script>
</body>
</html>