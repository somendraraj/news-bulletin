var myApp = angular.module('myApp', []);

myApp.directive('loading', function () {
      return {
        restrict: 'E',
        replace:true,
        template: '<div class="loading"><img src="http://www.nasa.gov/multimedia/videogallery/ajax-loader.gif" width="20" height="20" />Comming...</div>',
        link: function (scope, element, attr) {
              scope.$watch('loading', function (val) {
                  if (val)
                      $(element).show();
                  else
                      $(element).hide();
              });
        }
      }
  })


myApp.controller('newsCtrl', function($scope, $http) {

	  

  $scope.init = function() {
        $scope.loading = true;
      
        $http.get("http://localhost:8086/news/news").then(function(response){
          $scope.data = response.data; 
          $scope.loading = false; 
          console.log($scope.data); 
        });
      }


        

});