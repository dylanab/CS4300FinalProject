angular.module('frontExampleArticleList', [])
    .factory('ArticleList', function() {
	var articleList ={};
	articleList.articles = [
            {
		title: "Malaysia Airlines",
		author: "CNN",
		categories: ["Ratings Cow", "Tragedy"],
		date: "March 19, 2014",
		hits: 1000
            },
	    {
		title: "Cat is cute",
		author: "kitty",
		categories: ["Cute"],
		date: "March 11, 2014",
		hits: 100000
            },
            {
		title: "Obamacare obamacare obamacare",
		author: "Amerikan Konservative",
		categories: ["Politics"],
		date: "All day e'veryday",
		hits: 500
            },
	    {
		title: "Crimea Invasion",
		author: "BBC",
		categories: ["Politics","Russia Scary"],
		date: "March 6, 2014",
		hits: 2000
            }
	];
	return articleList;
    });
