package com.yinxm.customview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 播放进度条
 */
public class SpecialProgressbar extends View {
    // 背景
    private Path bgPath = new Path();
    private PathMeasure bgPm;
    private Paint bgPaint;
    private int bgPaintColor = Color.parseColor("#919799");
    private int topMargin = 10;
    // 控制点比例
    private int bzrCPointDenominatorX = 12;
    private int bzrCPointDenominatorY = 4;
    // 进度条宽度
    private final int paintWidth = 12 / 2;
    // 当前进度
    private Paint currentProgressPaint;
    private int currentProgressColor = Color.parseColor("#06C3C6");
    private Path currentProgressPath = new Path();
    private PathMeasure currentProgressMeasure = new PathMeasure(currentProgressPath, false);
    // 进度条头部光晕
    private Paint progressFrontPaint;
    private int progressFrontColor = Color.parseColor("#3406C3C6");
    private float[] progressFrontPosition = new float[2];
    private Matrix progressFrontMatrix = new Matrix();
    private int frontLength = 40;
    // 进度条头部圆形
    private Paint whitePaint;
    private int circleRadius = 4;
    private float maxProgress;
    private float currentProgress;
    // shader
    private Paint bgShaerPaint;
    private LinearGradient gradient;
    // clickable
    private Path clickablePath = new Path();
    private int clickableRange = 5;
    private Region clickableRegion;
    private Point eventPoint = new Point();
    private Path tempPath = new Path();
    private PathMeasure tempPathMeasure = new PathMeasure();
    private OnUserActionListener listener;

    public SpecialProgressbar(Context context) {
        super(context);
        initPaint();
    }

    public SpecialProgressbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public SpecialProgressbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        bgPaint = new Paint();
        bgPaint.setColor(bgPaintColor);
        bgPaint.setAntiAlias(true);
        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setStrokeWidth(paintWidth);
        currentProgressPaint = new Paint();
        currentProgressPaint.setColor(currentProgressColor);
        currentProgressPaint.setAntiAlias(true);
        currentProgressPaint.setStyle(Paint.Style.STROKE);
        currentProgressPaint.setStrokeWidth(paintWidth);
        progressFrontPaint = new Paint();
        progressFrontPaint.setColor(progressFrontColor);
        progressFrontPaint.setStyle(Paint.Style.STROKE);
        progressFrontPaint.setAntiAlias(true);
        progressFrontPaint.setStrokeWidth(paintWidth * 3);
        whitePaint = new Paint();
        whitePaint.setColor(Color.WHITE);
        whitePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        whitePaint.setAntiAlias(true);
        bgShaerPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawProgressBg(canvas);
        drawCurrentProgress(canvas);
        drawGradual(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        initProgressBgPath();
        initClickablePath();
        initShader();
    }

    private void initProgressBgPath() {
        // 两个控制点的位置
        int control1X = getMeasuredWidth() / bzrCPointDenominatorX;
        int control1Y = getMeasuredHeight() / bzrCPointDenominatorY;
        int control2X = getMeasuredWidth() - control1X;
        int control2Y = getMeasuredHeight() / bzrCPointDenominatorY;
        bgPath.reset();
        bgPath.moveTo(0, topMargin);
        bgPath.cubicTo(control1X, control1Y, control2X, control2Y, getMeasuredWidth(), topMargin);
        bgPm = new PathMeasure(bgPath, false);
    }

    private void initClickablePath() {
        int control1X = getMeasuredWidth() / bzrCPointDenominatorX;
        int control1Y = getMeasuredHeight() / bzrCPointDenominatorY;
        int control2X = getMeasuredWidth() - control1X;
        int control2Y = getMeasuredHeight() / bzrCPointDenominatorY;
        clickablePath.reset();
        clickablePath.moveTo(0, topMargin - clickableRange);
        clickablePath.cubicTo(control1X, control1Y - clickableRange, control2X, control2Y - clickableRange, getMeasuredWidth(), topMargin - clickableRange);
        clickablePath.moveTo(getMeasuredWidth(), topMargin - clickableRange);
        clickablePath.lineTo(getMeasuredWidth(), topMargin + clickableRange);
        clickablePath.cubicTo(control2X, control2Y + clickableRange, control1X, control1Y + clickableRange, 0, topMargin + clickableRange);
        clickablePath.lineTo(0, topMargin - clickableRange);
    }

    private boolean pointInPath(Path path, Point point) {
        if (clickableRegion == null) {
            RectF bounds = new RectF();
            path.computeBounds(bounds, true);
            clickableRegion = new Region();
            clickableRegion.setPath(path, new Region((int) bounds.left, (int) bounds.top, (int) bounds.right, (int) bounds.bottom));
        }
        return clickableRegion.contains(point.x, point.y);
    }

    private void initShader() {
        LinearGradient gradient = new LinearGradient(0, 0, getMeasuredWidth(), 0, new int[]{Color.DKGRAY, Color.TRANSPARENT, Color.DKGRAY}, null, Shader.TileMode.CLAMP);
        bgShaerPaint.setShader(gradient);
        bgShaerPaint.setStrokeWidth(getMeasuredWidth());
    }

    /**
     * 画背景
     *
     * @param canvas
     */
    private void drawProgressBg(Canvas canvas) {
        canvas.drawPath(bgPath, bgPaint);
    }

    /**
     * 画进度
     *
     * @param canvas
     */
    private void drawCurrentProgress(Canvas canvas) {
        float progressBgLength = bgPm.getLength();
        float progressLength = progressBgLength * currentProgress / maxProgress;
        currentProgressPath.reset();
        bgPm.getSegment(0, progressLength, currentProgressPath, true);
        currentProgressMeasure = new PathMeasure(currentProgressPath, false);
        currentProgressMeasure.getMatrix(currentProgressMeasure.getLength(), progressFrontMatrix, PathMeasure.POSITION_MATRIX_FLAG);
        progressFrontPosition[0] = 0;
        progressFrontPosition[1] = 0;
        progressFrontMatrix.mapPoints(progressFrontPosition);
        // 进度条头部的光晕的长度
        if (currentProgressMeasure.getLength() < frontLength) {
            canvas.drawCircle(progressFrontPosition[0], progressFrontPosition[1], circleRadius, whitePaint);
            canvas.drawPath(currentProgressPath, progressFrontPaint);
        } else {
            Path frontPath = new Path();
            currentProgressMeasure.getSegment(currentProgressMeasure.getLength() - frontLength, currentProgressMeasure.getLength(), frontPath, true);
            canvas.drawCircle(progressFrontPosition[0], progressFrontPosition[1], circleRadius, whitePaint);
            canvas.drawPath(currentProgressPath, currentProgressPaint);
            canvas.drawPath(frontPath, progressFrontPaint);
        }
    }

    /**
     * 画渐变
     *
     * @param canvas
     */
    private void drawGradual(Canvas canvas) {
        canvas.drawLine(0, 0, getMeasuredWidth(), 0, bgShaerPaint);
    }

    public float getMaxProgress() {
        return maxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    public float getCurrentProgress() {
        return currentProgress;
    }

    public void setCurrentProgress(int currentProgress) {
        this.currentProgress = currentProgress;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        eventPoint.set(Math.round(event.getX()), Math.round(event.getY()));
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                boolean valid = (pointInPath(clickablePath, eventPoint));
                if (valid) {
                    updateCurrentProgress(eventPoint);
                }
                invalidate();
                return valid;
            case MotionEvent.ACTION_MOVE:
                updateCurrentProgress(eventPoint);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                if (listener != null) {
                    if (currentProgress > maxProgress) {
                        currentProgress = maxProgress;
                    }
                    listener.onProgress(currentProgress);
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    private void updateCurrentProgress(Point eventPoint) {
        tempPath.reset();
        tempPath.moveTo(0, 0);
        tempPath.lineTo(eventPoint.x, 0);
        tempPath.lineTo(eventPoint.x, getMeasuredHeight());
        tempPath.lineTo(0, getMeasuredHeight());
        tempPath.close();
        findIntersectPath(tempPath, clickablePath);
        tempPathMeasure.setPath(tempPath, false);
        float length = tempPathMeasure.getLength() / 2;
        currentProgress = length * 1.0f / bgPm.getLength() * maxProgress;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private Path findIntersectPath(Path path1, Path path2) {
        path1.op(path2, Path.Op.INTERSECT);
        return path1;
    }

    public void setOnuserActionListener(OnUserActionListener listener) {
        this.listener = listener;
    }

    public interface OnUserActionListener {
        void onProgress(float currentProgress);
    }
}
