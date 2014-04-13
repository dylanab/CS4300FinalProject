function SearchCtrl($scope, $routeParams, filterFilter, orderByFilter, ArticleList) {
    $scope.articleList = ArticleList;
    $scope.filter = "$";

    $scope.search = {name:'', categories:'', $:''};
    $scope.searchBox = '';
    $scope.mainArticleId = {};
    $scope.changeCategoryTo = function(property) {
	$scope.search.categories = property;
	$scope.search.name = $scope.searchBox = '';

	if(!$routeParams.articleId) {
	    $scope.mainArticleId = filterFilter(orderByFilter($scope.articleList.articles, "-hits"), $scope.search.categories)[0].title;
	} else {
	    $scope.mainArticleId = $routeParams.articleId;
	}
    }
    if(!$routeParams.articleId) {
	$scope.mainArticleId = filterFilter(orderByFilter($scope.articleList.articles, "-hits"), $scope.search.categories)[0].title;

    } else {
	$scope.mainArticleId = $routeParams.articleId;
    }
    $scope.updateSearch = function () {
	$scope.search.name = $scope.searchBox;

    }
    $scope.changeMainArticle = function(title) {
	ArticleList
    }
}