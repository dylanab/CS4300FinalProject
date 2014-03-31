function addEventHandler(element, eventType, handlerFunction) {
    if(element.addEventListener) {
	element.addEventListener(eventType, handlerFunction, false);
    } else if(element.attachEvent) {
	element.attachEvent ('on' + eventType, handlerFunction);
    }
};
function removeEventHandler(element, eventType, handlerFunction) {
    if(element.removeEventListener) {
	element.removeEventListener(eventType, handlerFunction, false);
    } else if (element.detachEvent) {
	element.detachEvent('on' + eventType, handlerFunction);
    }
};
function clearSearchBar(e) {
    if(!e) var e = window.event;
    console.log("Clearing Search bar");
    document.getElementById('searchBar').value = "";
    removeEventHandler(document.getElementById('searchBar'), 'click', clearSearchBar);
};
window.onload=function() {
    addEventHandler(document.getElementById('searchBar'), 'click', clearSearchBar);
    console.log("Loading page");
};
