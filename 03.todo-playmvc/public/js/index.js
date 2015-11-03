(function($){
    $(function(){

        //// event handling ////

        // update checked
        $('.chk-task-done').click(function(){
            var $self = $(this);
            var $taskRoot = $self.parent().parent();

            var $form = $('#update-form');
            $('input[name=id]', $form).val($taskRoot.data('id'));
            $('input[name=content]', $form).val( $taskRoot.data('text'));
            $('input[name=done]').prop('checked', $self.is(':checked'));

            $form.submit();
        });

        //remove
        $('.btn-task-remove').click(function(){
            var $self = $(this);
            var $taskRoot = $(this).parent().parent();

            var $form = $('#remove-form');
            $('input[name=id]', $form).val($taskRoot.data('id'));
            $('input[name=content]', $form).val( $taskRoot.data('text'));
            $('input[name=done]').prop('checked', $self.is(':checked'));

            $form.submit();
        })
    });
})(jQuery);