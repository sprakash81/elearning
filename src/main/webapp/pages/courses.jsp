<!DOCTYPE html>
<html>
<head>
<title>eLearning - Add Courses</title>
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

	<div ng-controller="addCourseController">
		<form name="addCourseForm">
			<fieldset>
				<!-- <div>{{message}}</div> -->
				<div ng-class="{error: addCourseForm.code.$invalid}">
					<label>Code *: </label>
					<input type="text" name="code" ng-required="true" ng-maxlength="10" ng-model="newCourse.code" /> 
					<span ng-show="addCourseForm.code.$error.required">Field required</span> 
					<span ng-show="addCourseForm.code.$error.maxlength">This field has a size of 10 characters maximum</span>
				</div>
				<div>
					<label>Title *: </label>
					<input type="text" name="title" ng-required="true" ng-maxlength="255" ng-model="newCourse.title" /> 
					<span ng-show="addCourseForm.title.$error.required">Field required</span> 
					<span ng-show="addCourseForm.title.$error.maxlength">This field has a size of 255 characters maximum</span>
				</div>
				<button type="button" ng-click="submit()">Save</button>
			</fieldset>
		</form>
	</div>
	<br />
	<div ng-controller="listCoursesController">

		<table>
			<tr ng-repeat="course in courses">
				<td>{{ course.code }}</td>
				<td>{{ course.title }}</td>
			</tr>
		</table>

	</div>

	<script>
		var elearningApp = angular.module("elearingApp", []);

		elearningApp.controller("addCourseController", function($scope, $http) {
			/* $scope.message = ''; */
			$scope.newCourse = new Object();
			$scope.newCourse.code = ' ';
			$scope.newCourse.title = ' ';
			$scope.submit = function() {
				console.log("in here");
				console.log($scope.newCourse.toString());
				console.log($scope.newCourse.code);
				console.log($scope.newCourse.title);
				var dataObj = {'code':$scope.newCourse.code, 'title':$scope.newCourse.title};
				
				$http({
		            method :'POST',
		            url:'./add',
		            data: dataObj,
		            headers: {
                        "Content-Type": "application/json",
                        "Accept": "text/plain, application/json"
                    }
		        }).success(function(response){
		        	//update the shared model		        	
		        });
				
				/* $http.post("./add", $scope.newCourse).success(function(response) {
					$scope.message = response;
				}); */
			}
		});

		elearningApp.controller("listCoursesController",
				function($scope, $http) {
					$http.get("./list").success(function(response) {
						$scope.courses = response;
					});
				});
	</script>

</body>
</html>
