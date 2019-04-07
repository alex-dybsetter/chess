package net.alexblass.chess.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import net.alexblass.chess.ChessApplication;
import net.alexblass.chess.base.R;
import net.alexblass.chess.bus.event.PawnPromotedEvent;
import net.alexblass.chess.constant.Constants;
import net.alexblass.chess.model.piece.AbstractPiece;

public class DialogUtil {
    public static void showPawnPromotionDialog(Context context, final AbstractPiece pawnToPromote) {
        AlertDialog.Builder promotionDialog = new AlertDialog.Builder(context);
        promotionDialog
                .setTitle(R.string.dialog_title_pawn_promotion)
                .setItems(R.array.pawn_promotions, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                pawnToPromote.setName(Constants.QUEEN);
                                break;
                            case 1:
                                pawnToPromote.setName(Constants.BISHOP);
                                break;
                            case 2:
                                pawnToPromote.setName(Constants.ROOK);
                                break;
                            case 3:
                                pawnToPromote.setName(Constants.KNIGHT);
                                break;
                        }
                        ChessApplication.bus().post(new PawnPromotedEvent(pawnToPromote));
                    }
                }).create().show();
    }
}
