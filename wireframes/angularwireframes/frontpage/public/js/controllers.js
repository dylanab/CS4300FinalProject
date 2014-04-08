function HomeCtrl($scope, ArticleList) {
    console.log(ArticleList);
    $scope.articleList = ArticleList;
    $scope.filter = "$";
    $scope.search = {name:'', categories:'', $:''};
    $scope.changeCategoryTo = function(property) {
	$scope.search.categories = property;
    }
}
function SearchCtrl($scope, ArticleList) {
    $scope.articleList = ArticleList;
    $scope.filter = "$";
    $scope.search = {name:'', categories:'', $:''};
    $scope.changeCategoryTo = function(property) {
	$scope.search.categories = property;
    }

}