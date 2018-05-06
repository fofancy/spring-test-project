export default function inputFormController(PictureHttpService) {
    var self = this;
    
    self.addNewPicture = function () {
        PictureHttpService.post(
            self.newPicture,
            function() {
                alert('Picture was succesfully added');
            },
            function(err) {
                console.log(err);
                alert('Something went wrong')
            }
        )
    }
}