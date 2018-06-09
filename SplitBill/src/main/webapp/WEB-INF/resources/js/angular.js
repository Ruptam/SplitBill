var app=angular.module('myApp',["ngRoute"])
				.run(function($rootScope,$http,$location) {
					$rootScope.userDetails='';
					$rootScope.userFlag=false;
	
					$rootScope.logout=function(){
						
						$http({
							method:'GET',
							url:'sessionInvalidate'
						}).then(function(response) {
							console.log("Session Invalidation Success");
							$rootScope.userDetails='';
							$rootScope.userFlag=false;
							$location.path("/login");
						})
					}
	
					$rootScope.sessionValidation=function() {
						$http({
							method:'GET',
							url:'sessionValidation'
						}).then(function(response) {
							$rootScope.userDetails=response.data;
							$rootScope.userFlag=true;
						})
					}
			});

app.config(function($routeProvider){
	$routeProvider.when("/login",{
		templateUrl:"resources/html/login.html",
		controller:'userLogin'
	}).when("/register",{
		templateUrl:"resources/html/register.html"
	}).when("/user",{
		resolve : {
			"check" : function($rootScope, $location) {
				$rootScope.sessionValidation();
			}
		},
		templateUrl:"resources/html/user.html",
		controller:"userController"
	}).when("/logout",{
		templateUrl:"resources/html/login.html",
		controller:"logoutController"
	});
});


app.controller('myCtrl',function($scope,$http){
	$scope.user={};
	$scope.customer={};
	$scope.username='';
	$scope.showError=0;
	$scope.submitForm=function() {
		console.log($scope.user);
		
		$http({
			method:'POST',
			url:'saveUserDetails',
			data:$scope.user
		}).then(function(response) {
			console.log(response.data);
			if(response.data.ValidEmail == 'null') {
				console.log("exception");
				$scope.showError=1;
				$scope.user.email='';
			} else if (response.data.ValidPhoneNumber == 'null') {
				console.log("Phone Number Exception");
				$scope.showError=3;
				$scope.user.phoneNumber='';
			} else {
				$scope.generatedId=response.data;
				$scope.user={};
				$scope.showError=2;
			}
		});
	}
	
	
			
	$scope.viewExpenseDetails=function(value) {
		console.log("Details Below --->")
		console.log(value);
		$http({
			method:'POST',
			url:'viewExpenseDetails',
			data: value
		}).then(function(response) {
			console.log(response.data);
			$scope.shareDetails=response.data;
		})
	}
});


app.controller('userLogin',function($scope,$http,$rootScope,$location){
	
	$scope.friendPhnNo='';
	$scope.selectedFriend={};
	$scope.loginAuthentication=function() {
			
			console.log("Within Login");
			console.log($scope.customer.phoneNumber);
			console.log($scope.customer.password);
			
			$http({
				method:'POST',
				url:'authenticateUser',
				data:$scope.customer
			}).then(function(response) {
				$scope.customer={};
				console.log("Within Response");
				
				console.log("-----------");
				console.log(response.data.name);
				if(response.data != '') {
					console.log("success---> within If");
					
					$rootScope.userDetails=response.data;
					console.log($rootScope.userDetails.name);
					$rootScope.userFlag=true;
					$location.path("/user");
				}
			});
		}
	
		
	});




app.controller("logoutController", function($rootScope) {
	$rootScope.logout();
});

app.controller("expenseController", function($scope) {
	
	
});

app.controller("userController", function($scope,$http) {
	
	
	$scope.partition = function(s) {
		console.log("Within user controller partition method");
		$scope.part = s;
		console.log(s);
		if(s == 3 || s == 2) {
			console.log(s);
			$http({
				method:'GET',
				url:'showAllFriends',
			}).then(function(response) {
				if(response.data != '') {
					$scope.noFriends=0;
					console.log(response.data);
					$scope.showAllFriends=response.data;
				} else {
					$scope.noFriends=1;
				}
			});
		} else if (s == 1) {
			console.log(s);
			$http({
				method:'GET',
				url:'showRecentActivities',
			}).then(function(response){
				console.log("Within response");
				if(response.data != '') {
					console.log("response not null");
					console.log(response.data);
					$scope.recentActivities=response.data;
				}
				
			});
			
			$http({
				method:'GET',
				url:'showSummary',
			}).then(function(response) {
				console.log("Executing Succesfully");
				console.log(response.data);
				$scope.showSummary=response.data;
			})
		} 
	};
	$scope.showOwedSummaryDetails=function() {
		console.log("OwedSummaryDetails");
		$http({
			method: 'GET',
			url: 'owedSummaryDetails'
		}).then(function(response) {
			console.log(response.data);
			$scope.owedSummaryDetails=response.data;
			console.log($scope.owedSummaryDetails.share);
		});
		$http({
			method: 'GET',
			url: 'calculateOwedSummaryTotal',
		}).then(function(response) {
			console.log(response.data);
			$scope.owedTotalSummaryDetails=response.data;
			
		});
	}
	
	$scope.addFriendtoContact=function() {
		console.log("Within Friend addition method");
		console.log("Phn No to add --> ");
		console.log($scope.friendPhnNo);
		$http({
			method:'POST',
			url:'addFriendtoContact',
			data:$scope.friendPhnNo
		}).then(function(response) {
			console.log(response.data);
			if(response.data.status == 'success'){
				console.log("Friend Added");
				$scope.friendPhnNo='';
				$scope.sucessFriendAdded = 1;
			} else if (response.data.status == 'Invalid') {
				console.log("User Is not Registered to SplitBill");
				$scope.friendPhnNo='';
				$scope.sucessFriendAdded = 2;
			} else if (response.data.status == 'Registered') {
				console.log("User Is already added to list");
				$scope.friendPhnNo='';
				$scope.sucessFriendAdded = 3;
			}
		});
	}
	
	$scope.processExpense=function() {
		console.log($scope.selectedFriend);
		console.log($scope.selectedFriend.regIdr);
		$http({
			method:'POST',
			url: 'processExpense',
			data:$scope.selectedFriend
		}).then(function(response) {
			console.log("Within Response after processing Expense");
			if(response.data != 'Failed') {
				$scope.showSucess=1;
				$scope.selectedFriend={};
			}
		})
	}
})