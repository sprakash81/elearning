<!DOCTYPE html>
<html>
<head>
<title>eLearning - List and Add Trainings</title>
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
<body ng-app="elearingApp">
	<div ng-controller="listAccounts" ng-init="init()">
		<div>Search by Name: <input type="text" ng-model="filters.search"></div>
		</br>
		<table>
			<tr ng-repeat="account in accounts | filter:filters.search">
				<td id="accountId">{{ account.id }}</td>
				<td ng-click="getDetails(account.id)"><a href="">{{ account.firstName + " " + account.lastName }}</a></td>
				<td>{{ account.emailId }}</td>
			</tr>
		</table>
	</div>

	<script>
		var elearningApp = angular.module("elearingApp", []);
		
		elearningApp.controller("listAccounts", ['$scope','$http', function($scope, $http) {
			$scope.init = function(){
				$http.get("/elearning/accounts/list").success(function(response) {
					$scope.accounts = response;
				});
			}
			$scope.getDetails = function(id){
 				$http({
		            method :'GET',
		            url:'./list',
		            params: {'id': id},
		            headers: {
                        "Content-Type": "application/json",
                        "Accept": "application/json"
                    }
		        }).success(function(response){
		        	        	
		        }); 
			}	
		}]);
	</script>

</body>
</html>
