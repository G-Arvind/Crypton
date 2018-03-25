jQuery(document).ready(function(){
    console.log("hello");
  	var input = jQuery( '.input-block input' );
  	jQuery('.head-nav').css({"transition":"all 1.9s ease-in-out"});
  	jQuery('.image').css({"transition":"all 1.9s ease-in-out"});
  	function input_lenght(value) {
     	if  (value.val().length) {
            value.parent().addClass("input-lenght");
      } else {
         	value.parent().removeClass("input-lenght");
      }
    }

    function input_val(value) {
        if (value.val().length >= 3) {
            value.parent().addClass("input-val").removeClass("none");
        } else  {
            value.parent().removeClass("input-val").addClass("none");
        }
        value.parent().parent().find('.input-val').parent().addClass("enter");
        value.parent().parent().find('.none').parent().removeClass("enter");
    }

  	input.focus(function() {
      	jQuery(this).parent().addClass("input-lenght");
	});

  	input.blur(function() {
	    input_lenght(jQuery(this));
	});

  	input.after(function(){
      setTimeout(function(){
        	jQuery('.input-block input').attr({'value':''}).parent().removeClass("input-lenght input-val").addClass('none');
      }, 2000);
        input_lenght(jQuery(this));
      	input_val(jQuery(this));
    });

    input.keyup(function(){
    	input_val(jQuery(this));
	});

	jQuery('.submit-block input').click(function(){
			jQuery(this).parent().toggleClass('click');
	});
	var animationInProgress = false;
	jQuery('.head-nav span').click(function() {
		if (animationInProgress) return false;
		animationInProgress = true;
		var next_block = jQuery(this).attr('class');
		var visible_block = jQuery('.block-form:visible');
		var input_val_count = visible_block.find('.input-block').length;
		var time_anim = 900;
		var animation_type = time_anim;
		var pause = 0;
		if (jQuery('.checkbox-custom').prop('checked')) {
			jQuery('.head-nav').css({"transition-duration":"1.1s"});
  			jQuery('.image').css({"transition-duration":"1.1s"});
  			animation_type = 0;
  		} else {
  			jQuery('.head-nav').css({"transition-duration":"1.9s"});
  			jQuery('.image').css({"transition-duration":"1.9s"});
  		}
		if (!jQuery('.block-form:visible').is('.enter')) pause = 170;
		jQuery(this).parent().parent().removeClass().addClass(next_block);
    	var inter = setInterval(function() {
        	if ( 0 > input_val_count) clearInterval(inter);
        	visible_block.find('.input-block.input-lenght:eq(-1)').removeClass('input-lenght').addClass('marker');
        	input_val_count--;
    		setTimeout(function(){
				visible_block.slideUp(time_anim);
			}, pause);
    	}, time_anim*0.4/input_val_count+60);

		setTimeout(function(){
        	jQuery('#'+next_block).slideDown(time_anim);
        	var input_val_count = jQuery('#'+next_block).find('.input-block').length;
        	setTimeout(function(){
        		var inter = setInterval(function() {
        			if ( 0 > input_val_count) clearInterval(inter);
        			jQuery('#'+next_block).find('.input-block.marker:eq(0)').removeClass('marker').addClass('input-lenght');
        			input_val_count--;
    			}, time_anim*0.4/input_val_count+90);
    		}, pause);
      	}, animation_type+pause);
      	setTimeout(function(){
      		animationInProgress = false;
      	}, time_anim+pause);
	});

});
