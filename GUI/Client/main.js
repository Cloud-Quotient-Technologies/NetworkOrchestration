var Myapp = angular.module('Myapp', []);
Myapp.config(function($httpProvider) {
	  $httpProvider.defaults.useXDomain = true;
	  delete $httpProvider.defaults.headers.common['X-Requested-With'];
	});

Myapp.controller('CloudController', function($scope,$http)
	{
	$scope.bgp = {};
	$scope.show=false;
	$scope.showBGPoptions=false;
	$scope.IpNetw="0.0.0.0";
	$scope.display="";
	$scope.NumInst=0;
	$scope.UpdateReturn="";
	$scope.myFunc = function() 
		{
		$http.get('http://localhost:80/CloudManagementServer/rest/device/execute')
		.then(function(response)
				{
					$scope.test = response;
				});
		};
	$scope.getDetails = function()
		{
			$http.get('http://localhost:8000/CloudManagementServer/rest/device/getall')
			.then(function(response)
					{
						$scope.show=true;
						$scope.result = response;
					});
		
	    };
	    $scope.hideDetails = function()
	    {
	    	$scope.show=false;
	    	$scope.test = "";
	    };
	    $scope.submit = function(NumInst, IpNetw)
	    {
	    	var BGPdata = {
	    			NumInst: NumInst,
	    			IpNetw: IpNetw
	    	};
	    	$scope.bgp = BGPdata;
		    $http.post('http://localhost:8000/CloudManagementServer/rest/device/execute', BGPdata, {headers: {'Content-Type': 'application/json',
		    	'Access-Control-Allow-Origin': '*',
		    	'Access-Control-Allow-Headers': 'X-API-KEY, Origin, X-Requested-With, Content-Type, Accept, Access-Control-Request-Method',
		    	'Access-Control-Allow-Methods' : 'GET, POST, OPTIONS, PUT, DELETE'}})
		    .then( function successCallback(Response)
		    {
		    	$scope.display = "Command has been sent";
		    	if (Response.data)
		    		$scope.UpdateReturn = Response.data;
		    		$scope.statusval = Response.status;
		    		$scope.statustext = Response.statusText;
		    		$scope.headers = Response.headers();
		    	
		    },
		    function errorCallback(Response)
		    {
		    	$scope.display = "Some error occured during http post";
		    	$scope.UpdateReturn = "FAIL";
		    	$scope.statusval = Response.status;
		    	$scope.statustext = Response.statusText;
		    	$scope.headers = Response.headers();
		    })
	    	
	    };
	    $scope.toggleBGP = function()
	    {
	    	$scope.showBGPoptions = !$scope.showBGPoptions;
	    }
	  });