package com.jhallat.automated.testing.service;

import com.jhallat.automated.testing.domain.TestProject;
import io.reactivex.Observer;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import org.springframework.stereotype.Component;


@Component
public class TestProjectService {

    private final PublishSubject<TestProject> testProjectSubject;

    public TestProjectService() {
        testProjectSubject = PublishSubject.create();
    }

    public void addTestProject(TestProject testProject) {
        testProjectSubject.onNext(testProject);
    }

    public void subscribe(Observer<TestProject> subscriber) {
        testProjectSubject.subscribe(subscriber);
    }

    public void subscribe(Consumer<TestProject> onNext) {
        testProjectSubject.subscribe(onNext);
    }
}
