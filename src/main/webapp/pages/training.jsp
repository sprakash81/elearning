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
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js">
</script>
</head>
<body ng-app="elearingApp" ng-controller="listAccounts">
	<div ng-show="allowEdit == true">
		<fieldset>
			<!-- <select ng-options="training.course as training.courseTitle for training in trainings track by training.course.id"></select> -->
			<!-- <select ng-model="trainings.courseId" ng-options="">{{trainings.}}</select> -->
			<label>Course *: </label>
			<select>
				<option ng-repeat="course in courses" value="{{course.id}}" ng-model="training.courseId">{{course.code}} : {{course.title}}</option>
			</select>
			<label>Course Start Date *: </label>
			<input type="date" ng-required="true" name="courseStartDate" ng-model="training.courseStartDate"></input>
			<br/>
			<label>Grade : </label>
			<input type="text" maxlength="2" ng-maxlength="2" ng-model="training.courseGrade"></input>
			<label>Course Completion Date : </label>
			<input type="date" ng-required="true" name="courseCompletionDate" ng-model="training.courseCompletionDate"></input>
			<br/>
			<label>Course Comments :</label>
			<textarea rows="4" cols="50" maxlength="400" ng-maxlength="400" ng-model="training.courseComments"></textarea>
			<br/><br/>
			<button type="button" ng-click="save()">Save</button>
			<button type="button" ng-click="close()">Close</button>
		</fieldset>
		<fieldset>
			<table>
				<thead>
					<tr>
						<th>Course</th>
						<th>Title</th>
						<th>Start Date</th>
						<th>Grade</th>
						<th>Completion Date</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="training in trainings">
						<td><a href="" ng-click="setCurrentSelection(training)">{{training.courseCode}}</a></td>
						<td>{{training.courseTitle}}</td>
						<td>{{training.courseStartDate | date: "dd/MM/yyyy"}}</td>
						<td>{{training.courseGrade}}</td>
						<td>{{training.courseCompletionDate | date: "dd/MM/yyyy"}}</td>
					</tr>
				</tbody>
			</table>
		</fieldset>
	</div>
	<div ng-init="init()">
	<fieldset>
		<div>Search by Name: <input type="text" ng-model="filters.search"></div>
		<br>
		<table>
			<thead>
				<tr>
					<td>Full Name</td>
					<td>Email Id</td>
				</tr>
			</thead>
			<tr ng-repeat="account in accounts | filter:filters.search">
				<!-- <td id="accountId">{{ account.id }}</td> -->
				<td ng-click="getDetails(account.id)"><a href="">{{ account.firstName + " " + account.lastName }}</a></td>
				<td>{{ account.emailId }}</td>
			</tr>
		</table>
	</div>
	</fieldset>
	<script>
		var elearningApp = angular.module("elearingApp", []);
		elearningApp.controller("listAccounts", ['$scope','$http', function($scope, $http) {
			var allowEdit = false;
			var trainings = [];
			var training = [];
			var courses = [];
			var regexIso8601 = /^(\d{4}|\+\d{6})(?:-(\d{2})(?:-(\d{2})(?:T(\d{2}):(\d{2}):(\d{2})\.(\d{1,})(Z|([\-+])(\d{2}):(\d{2}))?)?)?)?$/;
			$scope.convertDateStringsToDates = function(input) {
			    // Ignore things that aren't objects.
			    if (typeof input !== "object") return input;

			    for (var key in input) {
			        if (!input.hasOwnProperty(key)) continue;

			        var value = input[key];
			        var match;
			        // Check for string properties which look like dates.
			        if (typeof value === "string" && (match = value.match(regexIso8601))) {
			            var milliseconds = Date.parse(match[0])
			            if (!isNaN(milliseconds)) {
			                input[key] = new Date(milliseconds);
			            }
			        } else if (typeof value === "object") {
			            // Recurse into object
			            convertDateStringsToDates(value);
			        }
			    }
			}
			$scope.init = function(){
				$http.get("/elearning/accounts/list").success(function(response) {
					$scope.accounts = response;
				});
				$http.get("/elearning/courses/list").success(function(response) {
					$scope.courses = response;
					//$scope.trainings.courses = $scope.courses;
				});
			}
			$scope.getDetails = function(id){
				$scope.trainings = null;
 				$http({
		            method :'GET',
		            url:'./list',
		            params: {'id': id},
		            headers: {
                        "Content-Type": "application/json",
                        "Accept": "application/json"
                    }
		        }).success(function(response){
		        	 $scope.allowEdit = true;		        	 
		        	 //$scope.trainings = $scope.convertDateStringsToDates(response);
		        	 if(response.length > 0){
			        	 var tempHolder = response;
			        	 for(var i=0; i<response.length; i++){
			        		 tempHolder[i].courseStartDate = new Date(response[i].courseStartDate);
			        		 if(null != response[i].courseCompletionDate){
			        			 // || 'undefined' != response[i].courseCompletionDate || !response[i].courseCompletionDate.trim().isEmpty()
			        		 	tempHolder[i].courseCompletionDate = new Date(response[i].courseCompletionDate);
			        		 }
			        		 //console.log("The original value is "+response[i].courseStartDate+" and new one is "+tempHolder[i].courseStartDate);
			        		 //console.log("The original value is "+response[i].courseCompletionDate+" and new one is "+tempHolder[i].courseCompletionDate);
			        	 }
			        	 
			        	 $scope.trainings = tempHolder;
			        	 $scope.training = $scope.trainings[0];
		        	 }else{
		        		 $scope.training = {};
		        		 //$scope.trainings.push(training);
		        	 }
		        }).error(function(response){
		        	 $scope.allowEdit = false;	
		        });
			}
			$scope.setCurrentSelection = function(training){
				//console.log('setting the value of training');
				$scope.training = training;
			}
			$scope.close = function(){
				$scope.allowEdit = false;
				$scope.training = null;
				$scope.trainings = null;
			}
			$scope.save = function() {
				if(null == $scope.trainings){ 
					$scope.trainings = {}; 
				}
				//$scope.trainings.add(training);
				$http({
		            method :'POST',
		            url:'./save',
		            data: $scope.trainings,
		            headers: {
                        "Content-Type": "application/json",
                        "Accept": "text/plain, application/json"
                    }
		        }).success(function(response){
		        	//$scope.close();
		        });
			}
		}]);
	</script>

</body>
</html>
