export default function gridController($scope, PictureHttpService) {
    var self = this;
    self.pager = {
        currentPage: 1
    };
    self.selected = {};

    self.filter = {
        title: ""
    }
    

    function init() {
        self.getPictures();
    }


    $scope.$watch(function () {
        return self.filter.title;
    }, function (newVal, oldval) {
        self.getPictures();
    })

    self.setPage = function (page) {
        if (page < 1 || page > self.pager.totalPages) {
            return;
        }

        self.pager.currentPage = page;
        // get pager object from service
        self.getPictures();
    }

    function getPages() {
        self.pager.currentPage = self.pager.currentPage || 1;

        var startPage, endPage;
        if (self.pager.totalPages <= 10) {
            // less than 10 total pages so show all
            startPage = 1;
            endPage = self.pager.totalPages;
        } else {
            // more than 10 total pages so calculate start and end pages
            if (self.pager.currentPage <= 6) {
                startPage = 1;
                endPage = 10;
            } else if (currentPage + 4 >= self.pager.totalPages) {
                startPage = self.pager.totalPages - 9;
                endPage = self.pager.totalPages;
            } else {
                startPage = self.pager.currentPage - 5;
                endPage = self.pager.currentPage + 4;
            }
        }
        function range(start, end) {
            var ans = [];
            for (let i = start; i < end; i++) {
                ans.push(i);
            }
            return ans;
        }
  
        // create an array of pages to ng-repeat in the pager control
        return range(startPage, endPage + 1);
    }

    self.getPictures = function () {
        PictureHttpService.getPage({
                pageIndex: self.pager.currentPage - 1,
                titleBeginsWith: self.filter.title
            },
            function (result) {
                self.currentPictures = result.data.content;
                self.pager.totalPages = result.data.totalPages;
                self.pager.pages = getPages();
            }
        )
    }


    console.log(self.currentPictures);

    self.deletePicture = function (index) {
        PictureHttpService.deletePicture(
            self.currentPictures[index],
            function (success) {
                self.currentPictures.splice(index, 1);
            },
            function (err) {
                console.log(err);
                alert("Some went wrong");
            }
        )
    }

    self.getTemplate = function (picture) {
        if (picture.id === self.selected.id) {
            return 'edit';
        }
        else return 'display';
    };

    self.editPicture = function (picture) {
        self.selected = angular.copy(picture);
    };

    self.reset = function () {
        self.selected = {};
    };

    self.updatePicture = function (picture) {
        PictureHttpService.put(
            picture,
            function () {
                console.log();
                self.reset();
                self.getPictures();
            },
            function (err) {
                alert("Something went wrong");
                console.log(err);
            }
        )
    }

    self.createPicture = function () {
        PictureHttpService.post(
            self.newPicture,
            function() {
                alert('Picture was succesfully added');
                
                self.getPictures();
            },
            function(err) {
                console.log(err);
                alert('Something went wrong')
            }
        )
    }

    
    init();
}