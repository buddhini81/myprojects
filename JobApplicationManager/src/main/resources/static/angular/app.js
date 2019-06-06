/**
 * 
 */

 var app = angular.module("JobAppManagerApp", []);
 
 app.directive('ngConfirmClick', [
     function() {
         return {
             link: function (scope, element, attr) {
                 var msg = attr.ngConfirmClick || "Are you sure you want to DELETE?";
                 var clickAction = attr.confirmedClick;
                 element.bind('click', function (event) {
                     if (window.confirm(msg)) {
                         scope.$eval(clickAction)
                     }
                 });
             }
         };
 }])
 
 
 app.controller("JobAppManagerController", function($scope, $http) {
	 
	 $scope.criteria = {};
	 $scope.jobData = [];

     $scope.addNew = function() {
    	 
         var method = "POST";
         var url = "/job/add";

         $http({
             method : method,
             url : url,
             data : angular.toJson($scope.job),
             headers : {
                 'Content-Type' : 'application/json'
             }
         }).then( _successSave, _error );
     };
     
     $scope.update = function() {
    	 
         var method = "PUT";
         var url = "/job/update";

         $http({
             method : method,
             url : url,
             data : angular.toJson($scope.edit),
             headers : {
                 'Content-Type' : 'application/json'
             }
         }).then( _successUpdate, _error );
     };
     
    $scope.findInfo = function() {
    	 
    	 if($scope.isFindValid()) {
    		 $(".alrt-failure").hide();
	         var method = "POST";
	         var url = "/job/find";
	
	         $http({
	             method : method,
	             url : url,
	             data : angular.toJson($scope.criteria),
	             headers : {
	                 'Content-Type' : 'application/json'
	             }
	         }).then( _successFind, _error );
    	 } else {
        	 
             $('.alrt-failure').show();
    	 }
     };
     
     $scope.setFindIn = function (findIn) {
    	    $scope.criteria.findIn = findIn;
     };
     
     $scope.isFindValid = function() {
    	
    	 var isValid = true;
    	 
    	 if(!(!!$scope.criteria.isSearchByDate)) {
    		 if(!(!!$scope.criteria.searchTerm)) {
    		   $('.alrt-failure').html('<strong>ERROR!</strong> Enter what you need to find OR search by a date range.');
    		   isValid = false;
    		 }
    	 } else {
    		 if(!(!!$scope.criteria.fromDate) || !(!!$scope.criteria.toDate)) {
    		   $('.alrt-failure').html('<strong>ERROR!</strong> Enter date range.');
      		   isValid = false;
      		 }
    	 }
    	 
    	 return isValid;
     };
     
     $scope.setDateSearch = function() {
    	 if($scope.criteria.isSearchByDate == '1') {
    		$('#fdate').css("visibility", "visible");
	    	$('#tdate').css("visibility", "visible");
	    } else if($scope.criteria.isSearchByDate == '0') { 
	    	$('#fdate').css("visibility", "hidden");
	    	$('#tdate').css("visibility", "hidden");
	    	$('#tdate').val('');
	    	$('#fdate').val('');
	    	$scope.criteria.isSearchByDate = null;
	    	$scope.criteria.fromDate = null;
	    	$scope.criteria.toDate = null;
	    }
     };
     
     $scope.showEdit = function(edit) {
    	 $scope.edit = edit;
    	 $('#myModalEdit').modal('show');
    	 
    	 
     };
     
     $scope.confirmedDelete = function(del) {
    	 var method = "DELETE";
         var url = "/job/delete";

         $http({
             method : method,
             url : url,
             data : angular.toJson(del),
             headers : {
                 'Content-Type' : 'application/json'
             }
         }).then( _successDelete, _error );
     }
     
     $scope.resetClick = function() {
    	 $('.reset').on('click', function() {
            (_reset());
    	 });
     }
          
     function _successFind(response) {
    	 
    	 if(response.data.length > 0) {
	    	 $scope.jobData = response.data;
	    	 
	    	 $('#myModal').modal('show');
    	 } else {
    		 $('#myModal').modal('hide');
    		 $('.alrt-message').html('<strong>MESSAGE!</strong> There are no results for your search.');
    		 $('.alrt-message').show();
        	 
        	 setTimeout(function() {
        	        $(".alrt-message").hide();
        	 }, 2000);
    	 }
     }
     

     function _successSave(response) {

    	 $('.alrt-success').show();
    	 
    	 setTimeout(function() {
    	        $(".alrt-success").hide();
    	 }, 2000);
    	     	 
    	 $('#add-form').find('input:text, textarea').val('');

     }
     
     function _successUpdate(response) {

    	 $('#alert-edit').show();
    	 
    	 setTimeout(function() {
    	        $("#alert-edit").hide();
    	 }, 2000);
     }
     
     function _successDelete(response) {
    	 $scope.findInfo();
    	 alert("DELETED!");
     }
     

     function _error(response) {
         console.log("ERROR");
     }
     
     function _reset() {
    	 $(this).closest('form').find('input[type=text], textarea').val('');
     }
               

 });