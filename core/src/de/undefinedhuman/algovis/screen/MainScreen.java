package de.undefinedhuman.algovis.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.undefinedhuman.algovis.engine.camera.CameraManager;
import de.undefinedhuman.algovis.gui.GuiManager;
import de.undefinedhuman.algovis.gui.elements.Slider;
import de.undefinedhuman.algovis.gui.event.ChangeEvent;
import de.undefinedhuman.algovis.gui.text.Text;
import de.undefinedhuman.algovis.gui.texture.GuiTexture;
import de.undefinedhuman.algovis.gui.transforms.constraints.PixelConstraint;
import de.undefinedhuman.algovis.gui.transforms.constraints.RelativeConstraint;
import de.undefinedhuman.algovis.gui.transforms.offset.CenterOffset;
import de.undefinedhuman.algovis.screen.algorithms.AlgorithmType;
import de.undefinedhuman.algovis.screen.algorithms.gui.Bar;
import de.undefinedhuman.algovis.screen.algorithms.gui.BarChart;
import de.undefinedhuman.algovis.screen.algorithms.gui.ButtonPanel;
import de.undefinedhuman.algovis.utils.Inputs;
import de.undefinedhuman.algovis.utils.Variables;

public class MainScreen implements Screen {

    public static MainScreen instance;
    public SpriteBatch batch;

    private BarChart barChart;

    private Text delayText;
    public Text infoText, updateText;

    private Thread algorithmThread;
    public long millis = 400;

    public MainScreen() {
        batch = new SpriteBatch();
        CameraManager.instance = new CameraManager();
    }

    @Override
    public void show() {
        Gdx.graphics.setResizable(true);
        Gdx.input.setInputProcessor(Inputs.instance);

        GuiManager.instance.addGui(
                new ButtonPanel()
                        .addChild(
                                new Slider(new GuiTexture("blank.png"), "blank.png", "Circle.png", 0.5f)
                                        .addChangeListener(new ChangeEvent() {
                                            @Override
                                            public void notify(float progress) {
                                                millis = (long) (Math.max(2000 * progress, 100));
                                                delayText.setText("Delay: " + millis);
                                            }
                                        })
                                        .addChild(
                                                delayText = (Text) new Text("Delay: 0")
                                                        .setFontSize(20)
                                                        .setPosition(new RelativeConstraint(0.5f), new RelativeConstraint(2.5f))
                                                        .setOffsetX(new CenterOffset())
                                        )
                                        .setColor(Variables.DARK_GREY)
                                        .set(new RelativeConstraint(0.5f), new RelativeConstraint(0.05f), new PixelConstraint(200), new PixelConstraint(10))
                                        .setOffsetX(new CenterOffset())
                        ),
                barChart = new BarChart().randomize(),
                updateText = (Text) new Text("")
                        .setFontSize(24)
                        .setPosition(new RelativeConstraint(0.01f), new RelativeConstraint(0.15f)),
                infoText = (Text) new Text("")
                        .setFontSize(24)
                        .setPosition(new RelativeConstraint(0.01f), new RelativeConstraint(0.1f))
        );
    }

    @Override
    public void render(float delta) {
        GuiManager.instance.update(delta);
        CameraManager.instance.update(delta);

        batch.setProjectionMatrix(CameraManager.camera.combined);
        batch.begin();
        GuiManager.instance.renderGui(batch, CameraManager.camera);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        CameraManager.instance.resize(width, height);
        GuiManager.instance.resize(width, height);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        if(algorithmThread != null && algorithmThread.isAlive())
            algorithmThread.interrupt();
        barChart.delete();
    }

    public void updateThread(AlgorithmType algorithmType) {
        if(algorithmThread != null && algorithmThread.isAlive())
            algorithmThread.interrupt();
        barChart.resetColors();
        for(Bar bar : barChart.getBars())
            bar.getTempIcon().setVisible(false);
        resetText();
        algorithmThread = new Thread(algorithmType.createInstance(barChart));
        algorithmThread.start();
        algorithmThread.setName(algorithmType.name());
    }

    public void reset() {
        if(algorithmThread != null && algorithmThread.isAlive())
            algorithmThread.interrupt();
        barChart.reset();
        resetText();
    }

    private void resetText() {
        updateText.setText("");
        infoText.setText("");
    }

}
